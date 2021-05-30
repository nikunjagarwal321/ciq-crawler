package commerceIq.crawler.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commerceIq.crawler.model.entities.ProductData;
import commerceIq.crawler.model.entities.ProductHistory;
import commerceIq.crawler.model.responseModels.CIQReturn;
import commerceIq.crawler.model.responseModels.PriceTrendResponse;
import commerceIq.crawler.model.responseModels.ProductDetailsResponse;
import commerceIq.crawler.model.responseModels.RatingsMap;
import commerceIq.crawler.repository.ProductDataRepository;
import commerceIq.crawler.repository.ProductHistoryRepository;
import commerceIq.crawler.service.utils.HtmlScrapper;
import commerceIq.crawler.service.utils.Utility;

import org.modelmapper.ModelMapper;

 /**
  * Manipulates & returns Responses based on the different API requests
  */
@Service
public class CrawlerService implements ICrawlerService{

    @Autowired
    HtmlScrapper htmlScrapper;
    
    @Autowired
    ProductDataRepository productDataRepository;

    @Autowired
    ProductHistoryRepository productHistoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired 
    Utility utility;

    Logger logger = LoggerFactory.getLogger(CrawlerService.class);
    
    /**
     * Receives url/productId as parameter, and returns Html Response after crawling that
     * html page or fetching it from DB
     */
    @Override
    public CIQReturn<String> GetHtml(String urlProductId) {
        logger.debug("Inside GetHtml Method");

        CIQReturn<String> ciqReturnObj = new CIQReturn<String>();
        try {
            String productId = utility.GetProductIdFromInputParameter(urlProductId);
            Optional<ProductData> previousProductData = productDataRepository.findById(productId);
            if(previousProductData.isPresent() && !utility.ShouldCrawlAgain(previousProductData.get().getLastCrawled())){
                ciqReturnObj.setReturnValue(previousProductData.get().getHtmlResponse());
                return ciqReturnObj;
            }
            ProductData productData = htmlScrapper.GetProductDataFromWeb(productId);
            ProductHistory productHistory = new ProductHistory(productId, productData.getLastCrawled(), productData.getOfferPrice());

            logger.debug(productData.toString());
            productDataRepository.save(productData);
            productHistoryRepository.save(productHistory);
            ciqReturnObj.setReturnValue(productData.getHtmlResponse());
        } catch (Exception e) {
            ciqReturnObj.setError(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return ciqReturnObj;

    }

    /**
     * Receives url/productId as parameter, and returns Product Details after crawling that
     * html page or fetching it from DB
     */
    @Override
    public CIQReturn<ProductDetailsResponse> GetProductDetails(String urlProductId) {
        logger.debug("Inside GetProductDetails Method");

        CIQReturn<ProductDetailsResponse> ciqReturnObj = new CIQReturn<ProductDetailsResponse>();
        try {
            String productId = utility.GetProductIdFromInputParameter(urlProductId);
            Optional<ProductData> previousProductData = productDataRepository.findById(productId);
            if(previousProductData.isPresent() && !utility.ShouldCrawlAgain(previousProductData.get().getLastCrawled())){
                ciqReturnObj.setReturnValue(this.MapProductPojoToResponse(previousProductData.get()));
                return ciqReturnObj;
            }
            ProductData productData = htmlScrapper.GetProductDataFromWeb(productId);
            ProductHistory productHistory = new ProductHistory(productId, productData.getLastCrawled(), productData.getOfferPrice());
            
            logger.debug(productData.toString());
            productDataRepository.save(productData);
            productHistoryRepository.save(productHistory);
            ciqReturnObj.setReturnValue(this.MapProductPojoToResponse(productData));
        }
        catch(Exception e) {
            logger.error(e.getMessage(), e);
            ciqReturnObj.setError(e.getMessage());
        }
        return ciqReturnObj;
        
    }

    /**
     * Receives url/productId and timestamp as parameter, and returns Product Details before the that timestamp
     * after fetching it from DB
     */
    @Override
    public CIQReturn<ProductDetailsResponse> GetProductDetailsHistory(String urlProductId, String stringDate) {
        logger.debug("Inside GetProductDetailsHistory Method");
        CIQReturn<ProductDetailsResponse> ciqReturnObj = new CIQReturn<ProductDetailsResponse>();
        
        try {
            Date lastDate = utility.StringToDate(stringDate);
            String productId = utility.GetProductIdFromInputParameter(urlProductId);
            ProductHistory previousProductPrice = productHistoryRepository.findProductBeforeTime(productId, lastDate);
            if(previousProductPrice == null) {
                logger.debug("No data exists in DB before give Timestamp");
                ciqReturnObj.setError("No data exists in DB before give Timestamp");
                return ciqReturnObj;
            }
            logger.debug("Previous Price Data in DB : ", previousProductPrice.toString());
            ProductData previousProductData = productDataRepository.findById(productId).get();
            previousProductData.setOfferPrice(previousProductPrice.getOfferPrice());
            ciqReturnObj.setReturnValue(this.MapProductPojoToResponse(previousProductData));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            ciqReturnObj.setError(e.getMessage());
        }
        return ciqReturnObj;
    }

    /**
     * Returns latest details of all the Products that are there in DB
     */
    @Override
    public CIQReturn<List<ProductDetailsResponse>> GetAllProductsData() {
        logger.debug("Inside GetAllProductsData Method");
        CIQReturn<List<ProductDetailsResponse>> ciqReturnObj = new CIQReturn<List<ProductDetailsResponse>>();
        
        try{
            List<ProductData> productDataList = productDataRepository.findAll();
            List<ProductDetailsResponse> productDetailsResponseList = new ArrayList<ProductDetailsResponse>();
            logger.debug("No. of products fetched from DB : " + productDataList.size());
            for (ProductData productData : productDataList) {
                ProductDetailsResponse productDetailsResponse = this.MapProductPojoToResponse(productData);
                productDetailsResponseList.add(productDetailsResponse);
            }
            ciqReturnObj.setReturnValue(productDetailsResponseList);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            ciqReturnObj.setError(e.getMessage());
        }
        return ciqReturnObj;
    }

    /**
     * Receives url or productId as parameter, and returns Prices of the product at different timestamp
     * when they have been queried
     */
    @Override
    public CIQReturn<List<PriceTrendResponse>> GetPriceTrend(String urlProductId) {
        logger.debug("Inside GetPriceTrend Method");
        CIQReturn<List<PriceTrendResponse>> ciqReturnObj = new CIQReturn<List<PriceTrendResponse>>();
        
        try{
            String productId = utility.GetProductIdFromInputParameter(urlProductId);
            List<ProductHistory> priceTrendList = productHistoryRepository.findByProductId(productId);
            List<PriceTrendResponse> priceTrendResponseList = new ArrayList<PriceTrendResponse>();
            logger.debug("No. of price data fetched from DB : " + priceTrendList.size());
            for (ProductHistory priceTrend : priceTrendList) {
                PriceTrendResponse priceTrendResponse = this.MapPriceTrendPojoToResponse(priceTrend);
                priceTrendResponseList.add(priceTrendResponse);
            }
            ciqReturnObj.setReturnValue(priceTrendResponseList);
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            ciqReturnObj.setError(e.getMessage());
        }
        return ciqReturnObj;
    }

    /**
     * Maps ProductData Entity to ProductDetailsResponseDTO
     */
    private ProductDetailsResponse MapProductPojoToResponse(ProductData productData){
        ProductDetailsResponse productDetailsResponse = modelMapper.map(productData, ProductDetailsResponse.class);
        RatingsMap ratingsMap = modelMapper.map(productData, RatingsMap.class);
        productDetailsResponse.setRatingsMap(ratingsMap);
        return productDetailsResponse;
    }
    
    /**
     * Maps ProductHistory Entity to PriceTrendResponseDTO
     */
    private PriceTrendResponse MapPriceTrendPojoToResponse(ProductHistory productHistory){
        PriceTrendResponse priceTrendResponse = modelMapper.map(productHistory, PriceTrendResponse.class);
        return priceTrendResponse;
    }

    
}
