package online.patologia.shop.controllers;

import online.patologia.shop.model.Cart;
import online.patologia.shop.model.Product;
import online.patologia.shop.service.ProductService;
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

    @GetMapping("/")
    public String getAllProductsForEveryone(Model model,@RequestParam(defaultValue = "0") int page) {
        model.addAttribute("product",new Product());

        model.addAttribute("products",productService.findAvailable(PageRequest.of(page,5)));
        model.addAttribute("currentPage",page);

            return "index";
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public String getFilteredProducts(@ModelAttribute("product") Product selected, Model model,@RequestParam(defaultValue = "0") int page) {
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
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @RequestMapping(value = "/product/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,Model model) {
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
        productService.deleteById(id);
        return "redirect:/panel";
    }

    @RequestMapping("/product/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = productService.getOne(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping("/product/{id}")
    public ModelAndView showProduct(@PathVariable(name = "id") Long id ) {
        ModelAndView mav = new ModelAndView("product");
        mav.addObject("cart",new Cart());
        Product product = productService.getOne(id);
        mav.addObject("product", product);
        mav.addObject("tooMuch",false);
        return mav;
    }


}
