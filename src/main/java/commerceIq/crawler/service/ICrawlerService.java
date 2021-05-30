package commerceIq.crawler.service;

import java.util.List;

import commerceIq.crawler.model.responseModels.CIQReturn;
import commerceIq.crawler.model.responseModels.PriceTrendResponse;
import commerceIq.crawler.model.responseModels.ProductDetailsResponse;



public interface ICrawlerService {

    public CIQReturn<String> GetHtml(String urlProductId);

    public CIQReturn<ProductDetailsResponse> GetProductDetails(String urlProductId);

    public CIQReturn<ProductDetailsResponse> GetProductDetailsHistory(String urlProductId, String stringDate);

    public CIQReturn <List<ProductDetailsResponse>> GetAllProductsData();

    public CIQReturn<List<PriceTrendResponse>> GetPriceTrend(String urlProductId);
}
