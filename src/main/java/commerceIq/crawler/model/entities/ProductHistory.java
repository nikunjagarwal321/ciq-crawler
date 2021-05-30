package commerceIq.crawler.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
  * Entity for Product History
  */
@Entity
@Table(name="ProductHistory")
public class ProductHistory {
        
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @Column(name="ProductId")
    private String productId;
    
    @Column(name="Timestamp")
    private Date timestamp;

    @Column(name="OfferPrice")
    private String offerPrice;

    public ProductHistory() {
    }

    public ProductHistory(String productId, Date timestamp, String offerPrice) {
        this.productId = productId;
        this.timestamp = timestamp;
        this.offerPrice = offerPrice;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
            " id='" + getId() + "'" +
            ", productId='" + getProductId() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", offerPrice='" + getOfferPrice() + "'" +
            "}";
    }

}
