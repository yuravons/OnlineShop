package online.patologia.shop.service;

import online.patologia.shop.model.Cart;
import online.patologia.shop.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;

    public void deleteByUser_Id(Long id) {
        cartRepo.deleteByUser_Id(id);
    }

    public List<Cart> findCartById(Long id) {
        return cartRepo.findCartById(id);
    }

    public List<Cart> findCartByIds(Long user_id, Long product_id) {
        return cartRepo.findCartByIds(user_id,product_id);
    }

    public Cart save(Cart cart) {
        return cartRepo.save(cart);
    }

    public List<Cart> findAll(){
        return cartRepo.findAll();
    }

    public Optional<Cart> findById(Long id) {
        return cartRepo.findById(id);
    }

    public void deleteById(Long id) {
        cartRepo.deleteById(id);
    }
}
