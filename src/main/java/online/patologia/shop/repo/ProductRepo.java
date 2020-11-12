package online.patologia.shop.repo;

import online.patologia.shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query(value = "SELECT * FROM PRODUCT WHERE STOCK>0 ORDER BY product_id DESC",nativeQuery = true)
    Page<Product> findAvailable(PageRequest pageRequest);
    @Query(value = "SELECT * FROM PRODUCT WHERE STOCK>0 AND CATEGORY=?1 ORDER BY product_id DESC",nativeQuery = true)
    Page<Product> findAvailableByCategory(String category,PageRequest pageRequest);
    @Query(value = "UPDATE PRODUCT SET STOCK=STOCK-?1 WHERE PRODUCT_ID=?2",nativeQuery = true)
    @Transactional
    @Modifying
    void reduceStock(int stock, Long id);

}
