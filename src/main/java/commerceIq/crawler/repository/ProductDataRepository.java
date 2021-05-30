package commerceIq.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import commerceIq.crawler.model.entities.ProductData;

 /**
  * Interacts with ProductData Table in DB
  */
public interface ProductDataRepository extends JpaRepository<ProductData, String> {
    
}
