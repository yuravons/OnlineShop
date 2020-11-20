package online.patologia.shop.controllers;

import online.patologia.shop.model.Order;
import online.patologia.shop.model.Product;
import online.patologia.shop.service.OrderService;
import online.patologia.shop.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PanelController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    final static Logger logger = Logger.getLogger(PanelController.class);

    @GetMapping("/panel/order/send/{id}")
    public String orderSend(@PathVariable("id") Long id,  Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start orderSend.");
        }

        Order order = orderService.getOne(id);
        if(order.isSend()) {
            order.setSend(false);
        } else {
            order.setSend(true);
        }
        orderService.save(order);
        List<Order> orders = new ArrayList<>();
        orderService.findAll().forEach(o -> orders.add(o));
        model.addAttribute("orders",orders);
        return "orders";
    }

    @GetMapping("/panel")
    public String getAllProducts(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start getAllProducts.");
        }

        model.addAttribute("product",new Product());
        List<Product> products = new ArrayList<>();
        productService.findAll().forEach(product -> products.add(product));
        model.addAttribute("products",products);
        return "panel";
    }

    @GetMapping("/panel/orders")
    public String getOrders(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start getOrders.");
        }

        List<Order> orders = new ArrayList<>();
        orderService.findAll().forEach(order -> orders.add(order));
        model.addAttribute("orders",orders);
        return "orders";
    }
}
