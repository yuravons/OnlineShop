package online.patologia.shop.service;

import online.patologia.shop.model.Product;
import online.patologia.shop.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Page<Product> findAvailable(PageRequest pageRequest) {
       return productRepo.findAvailable(pageRequest);
    }

    public Page<Product> findAvailableByCategory(String category, PageRequest pageRequest) {
        return productRepo.findAvailableByCategory(category,pageRequest);
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }

    public Product getOne(Long id) {
        return productRepo.getOne(id);
    }

    public void reduceStock(int stock, Long id) {
        productRepo.reduceStock(stock,id);
    }
}
