package online.patologia.shop.repo;

import online.patologia.shop.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<MyUser,Long> {
    MyUser findByUsername(String username);

//   @Query(value = "INSERT INTO my_user_item_list VALUES (?1,?2)", nativeQuery = true)
//   void addProductToCart(Long user_id, Long product_id);
}
