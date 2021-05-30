package commerceIq.crawler.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import commerceIq.crawler.constants.RouteConstants;
import commerceIq.crawler.model.responseModels.CIQReturn;
import commerceIq.crawler.model.responseModels.PriceTrendResponse;
import commerceIq.crawler.model.responseModels.ProductDetailsResponse;
import commerceIq.crawler.service.ICrawlerService;

 /**
  * Routes different api requests to their respective services
  */
@RestController
public class CrawlerController {
    @Autowired
    ICrawlerService crawlerService;
    
    @GetMapping(RouteConstants.GetHtml)
    public CIQReturn<String> GetHtml(String urlid) {
        Date incomingRequestTime = new Date();
        CIQReturn<String> ciqReturn = crawlerService.GetHtml(urlid);
        ciqReturn.setResponseTimeSeconds((new Date().getTime() - incomingRequestTime.getTime())/1000.0);
        return ciqReturn;
    }

    @GetMapping(RouteConstants.GetProductDetails)
    public CIQReturn<ProductDetailsResponse> GetProductDetails(String urlid) {
        Date incomingRequestTime = new Date();
        CIQReturn<ProductDetailsResponse> ciqReturn = crawlerService.GetProductDetails(urlid);
        ciqReturn.setResponseTimeSeconds((new Date().getTime() - incomingRequestTime.getTime())/1000.0);
        return ciqReturn;
    }

    @GetMapping(RouteConstants.GetProductDetailsHistory)
    public CIQReturn<ProductDetailsResponse> GetProductDetailsHistory(String urlid, String date) {
        Date incomingRequestTime = new Date();
        CIQReturn<ProductDetailsResponse> ciqReturn = crawlerService.GetProductDetailsHistory(urlid, date);
        ciqReturn.setResponseTimeSeconds((new Date().getTime() - incomingRequestTime.getTime())/1000.0);
        return ciqReturn;
    }

    @GetMapping(RouteConstants.GetAllProductsData)
    public CIQReturn<List<ProductDetailsResponse>> GetAllProductsData() {
        Date incomingRequestTime = new Date();
        CIQReturn<List<ProductDetailsResponse>> ciqReturn = crawlerService.GetAllProductsData();
        ciqReturn.setResponseTimeSeconds((new Date().getTime() - incomingRequestTime.getTime())/1000.0);
        return ciqReturn;
    }

    @GetMapping(RouteConstants.GetPriceTrend)
    public CIQReturn<List<PriceTrendResponse>> GetPriceTrend(String urlid) {
        Date incomingRequestTime = new Date();
        CIQReturn<List<PriceTrendResponse>> ciqReturn = crawlerService.GetPriceTrend(urlid);
        ciqReturn.setResponseTimeSeconds((new Date().getTime() - incomingRequestTime.getTime())/1000.0);
        return ciqReturn;
    }

}
