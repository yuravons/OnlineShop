package online.patologia.shop.repo;

import online.patologia.shop.model.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProviderRepo extends JpaRepository<Provider,Long> {
    @Query(value = "SELECT * FROM PROVIDER WHERE STOCK>0 ORDER BY provider_id DESC",nativeQuery = true)
    Page<Provider> findAvailable(PageRequest pageRequest);

}