package online.patologia.shop.controllers;

import online.patologia.shop.model.Cart;
import online.patologia.shop.model.Product;
import online.patologia.shop.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    final static Logger logger = Logger.getLogger(ProductController.class);

    @GetMapping("/")
    public String getAllProductsForEveryone(Model model,@RequestParam(defaultValue = "0") int page) {
        if(logger.isDebugEnabled()){
            logger.debug("start getAllProductsForEveryone.");
        }

        model.addAttribute("product",new Product());

        model.addAttribute("products",productService.findAvailable(PageRequest.of(page,5)));
        model.addAttribute("currentPage",page);

            return "index";
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public String getFilteredProducts(@ModelAttribute("product") Product selected, Model model,@RequestParam(defaultValue = "0") int page) {
        if(logger.isDebugEnabled()){
            logger.debug("start getFilteredProducts.");
        }

        List<Product> products = new ArrayList<>();
        if (selected.getCategory().equals("ALL")) {
            model.addAttribute("products",productService.findAvailable(PageRequest.of(page,5)));
        }  else {
            model.addAttribute("products",productService.findAvailableByCategory(selected.getCategory(),PageRequest.of(page,5)));
        }

        model.addAttribute("currentPage",page);
        return "index";
    }



    @RequestMapping("/product/new")
    public String showNewProductPage(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start showNewProductPage.");
        }

        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @RequestMapping(value = "/product/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start saveProduct.");
        }

        if (bindingResult.hasErrors()) {
            return "new_product";
        } else {
            productService.save(product);
            model.addAttribute("products",productService.findAll());
            return "panel";
        }
    }

    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start updateProduct.");
        }

        if (bindingResult.hasErrors()) {
            return "edit_product";
        } else {
            productService.save(product);
            model.addAttribute("products",productService.findAll());
            return "panel";
        }
    }


    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        if(logger.isDebugEnabled()){
            logger.debug("start delete.");
        }

        productService.deleteById(id);
        return "redirect:/panel";
    }

    @RequestMapping("/product/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
        if(logger.isDebugEnabled()){
            logger.debug("start showEditProductPage.");
        }

        ModelAndView mav = new ModelAndView("edit_product");
        Product product = productService.getOne(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping("/product/{id}")
    public ModelAndView showProduct(@PathVariable(name = "id") Long id ) {
        if(logger.isDebugEnabled()){
            logger.debug("start showProduct.");
        }

        ModelAndView mav = new ModelAndView("product");
        mav.addObject("cart",new Cart());
        Product product = productService.getOne(id);
        mav.addObject("product", product);
        mav.addObject("tooMuch",false);
        return mav;
    }


}
