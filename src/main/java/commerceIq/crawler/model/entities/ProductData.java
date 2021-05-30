package commerceIq.crawler.model.entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
  * Entity for Product Data table
  */
@Entity
@Table(name="ProductData")
public class ProductData {
    @Id
    @Column(name="ProductId")
    private String productId;
    
    @Lob
    @Column(name="htmlResponse")
    private String htmlResponse;

    @Column(name="Title")
    private String title;

    @Column(name="OfferPrice")
    private String offerPrice;

    @Column(name="Description", columnDefinition="LONGTEXT")
    private String description;

    @Column(name="RatingsCount")
    private String ratingsCount;

    @Column(name="FiveStar")
    private String fivestar;

    @Column(name="FourStar")
    private String fourstar;

    @Column(name="ThreeStar")
    private String threestar;

    @Column(name="TwoStar")
    private String twostar;

    @Column(name="OneStar")
    private String onestar;

    @Column(name="LastCrawled")
    private Date lastCrawled;

    public ProductData() {
    }


    public ProductData(String productId, String htmlResponse, String title, String offerPrice, String description, String ratingsCount, String fivestar, String fourstar, String threestar, String twostar, String onestar, Date lastCrawled) {
        this.productId = productId;
        this.htmlResponse = htmlResponse;
        this.title = title;
        this.offerPrice = offerPrice;
        this.description = description;
        this.ratingsCount = ratingsCount;
        this.fivestar = fivestar;
        this.fourstar = fourstar;
        this.threestar = threestar;
        this.twostar = twostar;
        this.onestar = onestar;
        this.lastCrawled = lastCrawled;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getHtmlResponse() {
        return this.htmlResponse;
    }

    public void setHtmlResponse(String htmlResponse) {
        this.htmlResponse = htmlResponse;
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

    public String getRatingsCount() {
        return this.ratingsCount;
    }

    public void setRatingsCount(String ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getFivestar() {
        return this.fivestar;
    }

    public void setFivestar(String fivestar) {
        this.fivestar = fivestar;
    }

    public String getFourstar() {
        return this.fourstar;
    }

    public void setFourstar(String fourstar) {
        this.fourstar = fourstar;
    }

    public String getThreestar() {
        return this.threestar;
    }

    public void setThreestar(String threestar) {
        this.threestar = threestar;
    }

    public String getTwostar() {
        return this.twostar;
    }

    public void setTwostar(String twostar) {
        this.twostar = twostar;
    }

    public String getOnestar() {
        return this.onestar;
    }

    public void setOnestar(String onestar) {
        this.onestar = onestar;
    }

    public Date getLastCrawled() {
        return this.lastCrawled;
    }

    public void setLastCrawled(Date lastCrawled) {
        this.lastCrawled = lastCrawled;
    }
   

    @Override
    public String toString() {
        return "{" +
            " productId='" + getProductId() + "'" +
            ", title='" + getTitle() + "'" +
            ", offerPrice='" + getOfferPrice() + "'" +
            ", description='" + getDescription() + "'" +
            ", ratingsCount='" + getRatingsCount() + "'" +
            ", fivestar='" + getFivestar() + "'" +
            ", fourstar='" + getFourstar() + "'" +
            ", threestar='" + getThreestar() + "'" +
            ", twostar='" + getTwostar() + "'" +
            ", onestar='" + getOnestar() + "'" +
            ", lastCrawled='" + getLastCrawled() + "'" +
            "}";
    }

}
