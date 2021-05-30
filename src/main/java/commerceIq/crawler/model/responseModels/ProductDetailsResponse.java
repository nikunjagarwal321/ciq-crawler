package commerceIq.crawler.model.responseModels;

/**
  * Response DTO for Product Details 
  */
public class ProductDetailsResponse {
    private String title;
    private String offerPrice;
    private String description;
    private RatingsMap ratingsMap;

    public ProductDetailsResponse() {
    }

    public ProductDetailsResponse(String title, String offerPrice, String description, RatingsMap ratingsMap) {
        this.title = title;
        this.offerPrice = offerPrice;
        this.description = description;
        this.ratingsMap = ratingsMap;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOfferPrice() {
        return this.offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RatingsMap getRatingsMap() {
        return this.ratingsMap;
    }

    public void setRatingsMap(RatingsMap ratingsMap) {
        this.ratingsMap = ratingsMap;
    }

    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", offerPrice='" + getOfferPrice() + "'" +
            ", description='" + getDescription() + "'" +
            ", ratingsMap='" + getRatingsMap() + "'" +
            "}";
    }


}
