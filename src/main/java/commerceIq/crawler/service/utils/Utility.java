package commerceIq.crawler.service.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import commerceIq.crawler.constants.ServiceConstants;

 /**
  * Contains different utility functions for used by other services 
  */
@Component
public class Utility {

    /**
     * Receives amazon url and returns the product id according to the given pattern
     * https://www.amazon.in/dp/B00QJDOEAO
     * https://www.amazon.in/gp/product/B0725W7Q38/
     * https://www.amazon.in/productname/dp/B0728C3C4K
     */
    private String GetProductIdFromUrl(String url){
        String productId = "";
        String[] paths = url.split("/");
        for (int i = 0; i < paths.length; i++) {
            if(paths[i].equals(ServiceConstants.AmazonUrlDP)) {
                productId = paths[i+1];
                break; 
            } else if(paths[i].equals(ServiceConstants.AmazonUrlGP)) {
                productId = paths[i+2];
                break; 
            }
        }
        return productId;
    }

    /**
     * Receives product id or url and returns productId after validation checks
     */
    public String GetProductIdFromInputParameter(String urlProductId) {
        String productId;
        try {
            URL obj = new URL(urlProductId);
            obj.toURI();
            productId = this.GetProductIdFromUrl(urlProductId);
         } catch (MalformedURLException | URISyntaxException e) {
            productId = urlProductId;
        }
        return productId;
    }   
    
    /**
     * checks whether it should crawl again or not based on whether last crawled time is >, <, = 1 hour
     */
    public boolean ShouldCrawlAgain(Date lastCrawledTime) {
        Date currentDate = new Date();
        long timeDifference = currentDate.getTime() - lastCrawledTime.getTime();
        if(timeDifference <= 3600000)
            return false;
        return true;
    }

    /**
     * Converts String format to Date based on specified format
     */
    public Date StringToDate(String stringDate) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
        Date date = formatter.parse(stringDate);
        return date;
    }
}
