package online.patologia.shop.service;

import online.patologia.shop.model.Order;
import online.patologia.shop.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public Order getOne(Long id) {
        return orderRepo.getOne(id);
    }

    public Order save(Order order) {
        return orderRepo.save(order);
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

}
