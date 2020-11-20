package online.patologia.shop.controllers;

import online.patologia.shop.model.*;
import online.patologia.shop.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ItemService itemService;

    final static Logger logger = Logger.getLogger(CartController.class);

    @GetMapping("/cart/clear")
    public String clearCart(@RequestParam(name = "page", defaultValue = "0") int page, Model model,Principal principal) {
        if(logger.isDebugEnabled()){
            logger.debug("start clearCart.");
        }

        model.addAttribute("finalizeDone",true);
        model.addAttribute("product",new Product());
        model.addAttribute("products", productService.findAvailable(PageRequest.of(page,5)));
        model.addAttribute("currentPage",page);

        cartService.deleteByUser_Id(userService.findByUsername(principal.getName()).getId());
        return "index";
    }



    @RequestMapping(value = "/cart/finalize/save",method = RequestMethod.POST)
    public String finalizeSave( @ModelAttribute("addressandpersonaldata") @Valid AddressAndPersonalData addressAndPersonalData,BindingResult bindingResult, Principal principal, Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start finalizeSave.");
        }

        List<Cart> listOfCarts = cartService.findCartById(userService.findByUsername(principal.getName()).getId());
        List<Item> listOfItems = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            Double priceForAll=0.0;
            for (Cart cart : listOfCarts) {
                priceForAll+=cart.getPrice();
            }
            model.addAttribute("priceForAll",priceForAll);
            return "finalize";
        }


        Double priceForAll=0.0;
        for (Cart cart : listOfCarts) {
            priceForAll+=cart.getPrice();
            listOfItems.add(itemService.save(new Item(cart.getProduct_id(),cart.getQuantity())));
            productService.reduceStock(cart.getQuantity(),cart.getProduct_id());
        }

        Order order = new Order(listOfItems,addressAndPersonalData,priceForAll);
        addressService.save(addressAndPersonalData);
        orderService.save(order);

        return "redirect:/cart/clear";
    }

    @RequestMapping(value = "/cart/add/{id}",method = RequestMethod.POST)
    public String addProducts(@ModelAttribute("cart") Cart cart, @PathVariable("id") Long product_id, Principal principal) {
        if(logger.isDebugEnabled()){
            logger.debug("start addProducts.");
        }

        Long user_id = userService.findByUsername(principal.getName()).getId();
        Product product = productService.getOne(product_id);
        Cart newCart = new Cart();
        newCart.setProduct_id(product_id);
        newCart.setQuantity(cart.getQuantity());
        newCart.setUser_id(user_id);
        newCart.setPrice(product.getPrice()*newCart.getQuantity());
        if (cartService.findCartByIds(user_id,product_id).isEmpty()) {
            cartService.save(newCart);
        } else {
            List<Cart> cartToUpdate = cartService.findCartByIds(user_id,product_id);
            Cart cart1 = cartToUpdate.get(0);
            if(cart1.getQuantity()+cart.getQuantity() > product.getStock()) {
                return "redirect:/product/"+product_id+"?error=notenough";
            }
            cart1.setQuantity(cart1.getQuantity() + cart.getQuantity());
            cartService.save(cart1);
        }
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String showCart(Model model,Principal principal) {
        if(logger.isDebugEnabled()){
            logger.debug("start showCart.");
        }

        List<CartTest> allProducts = new ArrayList<>();
        Long user_id = userService.findByUsername(principal.getName()).getId();
        cartService.findAll().forEach(i -> {
            if (i.getUser_id() == user_id) {
                Product product = productService.getOne(i.getProduct_id());
                allProducts.add(new CartTest(i.getId(),user_id,product.getId(),i.getQuantity(),
                        product.getName(),i.getPrice(),product.getProducer(),
                        product.getImageSource()));
            }
        });
        model.addAttribute("cart",allProducts);
        return "new_cart";
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteItemFromCart(@PathVariable("id") Long id, Model model,Principal principal) {
        if(logger.isDebugEnabled()){
            logger.debug("start deleteItemFromCart.");
        }

        List<CartTest> allProducts = new ArrayList<>();
        Long user_id = userService.findByUsername(principal.getName()).getId();
        Optional<Cart> cartById = cartService.findById(id);
        if (user_id == cartById.get().getUser_id()) {
            cartService.deleteById(id);
        }
        cartService.findAll().forEach(i -> {
            if (i.getUser_id() == user_id) {
                allProducts.add(new CartTest(i.getId(),user_id,
                        productService.getOne(i.getProduct_id()).getId(),i.getQuantity(),
                        productService.getOne(i.getProduct_id()).getName(),i.getPrice(),
                        productService.getOne(i.getProduct_id()).getProducer(), productService.getOne(i.getProduct_id()).getImageSource()));
            }
        });
        model.addAttribute("cart",allProducts);
        return "new_cart";
    }
}
