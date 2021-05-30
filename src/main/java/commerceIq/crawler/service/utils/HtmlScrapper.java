package commerceIq.crawler.service.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import commerceIq.crawler.constants.ServiceConstants;
import commerceIq.crawler.model.entities.ProductData;

 /**
  * Responsible for scraping HTML 
  */
@Component
public class HtmlScrapper {

    Logger logger = LoggerFactory.getLogger(HtmlScrapper.class);

    /**
     * Fetches given url from Web and returns html response
     */
    private Document GetHtmlDocument(String url) {
        Document htmlResponse = new Document(url);
        try {
            Connection connection = Jsoup.connect(url);
            connection.userAgent(this.GetRandomUserAgent());
            htmlResponse= connection.get();
            logger.debug("HTML Response fetched with url : " + url);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return htmlResponse;
        }
        return htmlResponse;
    }

    /**
     * Returns text of idElement from htmlResponse
     */
    private String GetHtmlTextFromId(Document htmlResponse, String htmlId){
        return htmlResponse.getElementById(htmlId).text();
    }

    /**
     * Updates Ratings attributes of ProductData from htmlRespnse
     */
    private ProductData UpdateProductRatingsFromHtml(Document htmlResponse, ProductData productData){
        
        Elements histogramTableData = htmlResponse.getElementsByClass(ServiceConstants.RatingsTableHtmlClass);
        productData.setFivestar(histogramTableData.get(0).getElementsByClass(ServiceConstants.RatingsValueHtmlClass).text());
        productData.setFourstar(histogramTableData.get(1).getElementsByClass(ServiceConstants.RatingsValueHtmlClass).text());
        productData.setThreestar(histogramTableData.get(2).getElementsByClass(ServiceConstants.RatingsValueHtmlClass).text());
        productData.setTwostar(histogramTableData.get(3).getElementsByClass(ServiceConstants.RatingsValueHtmlClass).text());
        productData.setOnestar(histogramTableData.get(4).getElementsByClass(ServiceConstants.RatingsValueHtmlClass).text());
        return productData;
    }
    
    /**
     * Generates random UserAgent to bypass amazon's defender 
     */
    private String GetRandomUserAgent() {
        List<String> userAgentList = Arrays.asList(ServiceConstants.UserAgent1,
                                                    ServiceConstants.UserAgent2,
                                                    ServiceConstants.UserAgent3,
                                                    ServiceConstants.UserAgent4);
        int randomAgentIndex = (int)(Math.floor(Math.random()*4));
        return userAgentList.get(randomAgentIndex);
    }

    /**
     * Returns Product details with the given productId after fetching it from web and scraping it 
     */
    public ProductData GetProductDataFromWeb(String productId) {
        ProductData productData = new ProductData();
        Document htmlResponse = GetHtmlDocument(ServiceConstants.AmazonBaseUrl + productId);
        productData.setProductId(productId);
        productData.setLastCrawled(new Date());
        productData.setHtmlResponse(htmlResponse.toString());
        productData.setTitle(GetHtmlTextFromId(htmlResponse,ServiceConstants.ProductTitleHtmlId));
        productData.setOfferPrice(GetHtmlTextFromId(htmlResponse,ServiceConstants.OfferPriceHtmlId));
        productData.setDescription(GetHtmlTextFromId(htmlResponse,ServiceConstants.ProductDescriptionHtmlId));
        productData.setRatingsCount(GetHtmlTextFromId(htmlResponse,ServiceConstants.RatingsCountHtmlId));
        productData = UpdateProductRatingsFromHtml(htmlResponse, productData);
        return productData;
    }


}
