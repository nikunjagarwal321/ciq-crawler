package commerceIq.crawler.model.responseModels;

import java.util.Date;
 
/**
  * Response DTO for Price Trend 
  */
public class PriceTrendResponse {
    private Date timestamp;
    private String offerPrice;

    public PriceTrendResponse() {
    }

    public PriceTrendResponse(Date timestamp, String offerPrice) {
        this.timestamp = timestamp;
        this.offerPrice = offerPrice;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getOfferPrice() {
        return this.offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }


    @Override
    public String toString() {
        return "{" +
            " timestamp='" + getTimestamp() + "'" +
            ", offerPrice='" + getOfferPrice() + "'" +
            "}";
    }

}
