package online.patologia.shop.controllers;

import online.patologia.shop.model.AddressAndPersonalData;
import online.patologia.shop.model.Cart;
import online.patologia.shop.service.CartService;
import online.patologia.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ShipmentController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart/finalize")
    public String getShipment(Model model, Principal principal) {
        model.addAttribute("addressandpersonaldata",new AddressAndPersonalData());
        List<Cart> listOfCarts = cartService.findCartById(userService.findByUsername(principal.getName()).getId());
        Double priceForAll=0.0;
        for (Cart cart : listOfCarts) {
            priceForAll+=cart.getPrice();
        }
        model.addAttribute("priceForAll",priceForAll);
        return "shipment";
    }
}
