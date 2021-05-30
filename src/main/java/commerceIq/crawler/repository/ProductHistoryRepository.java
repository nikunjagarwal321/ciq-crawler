package commerceIq.crawler.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import commerceIq.crawler.model.entities.ProductHistory;

 /**
  * Interacts with ProductHistory Table in DB
  */
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Integer>{
    
    @Query(value = "Select p from ProductHistory p where p.productId = :productId")
    public List<ProductHistory> findByProductId(@Param("productId") String productId);

    @Query(value = "Select * from product_history p where p.product_id = :productId And p.timestamp <= :lastTime order by p.timestamp desc Limit 1", nativeQuery = true)
    public ProductHistory findProductBeforeTime(@Param("productId") String productId,@Param("lastTime") Date lastTime);
}


