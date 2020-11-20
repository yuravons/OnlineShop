package online.patologia.shop.controllers;

import online.patologia.shop.model.MyUser;
import online.patologia.shop.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    final static Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping("/user/new")
    public String showNewUserForm(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start showNewUserForm.");
        }

        MyUser myUser = new MyUser();
        model.addAttribute("user", myUser);
        return "register";
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("user") @Valid MyUser myUser, BindingResult bindingResult, Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start saveProduct.");
        }

        myUser.setUsername(myUser.getUsername().trim());
        myUser.setPassword(myUser.getPassword().trim());
        myUser.setPasswordMatcher(myUser.getPasswordMatcher().trim());
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (userService.findByUsername(myUser.getUsername()) != null) {
            model.addAttribute("accountExist",true);
            return "/register";
        }
        if (!myUser.getPassword().equals(myUser.getPasswordMatcher())) {
            model.addAttribute("wrongPass", true);
            return "/register";
        }
        myUser.setRole("ROLE_USER");
        myUser.setPassword(passwordEncoder().encode(myUser.getPassword()));
        userService.save(myUser);
        model.addAttribute("success",true);
        return "login";

    }

    @RequestMapping("/login.html")
    public String login() {
        if(logger.isDebugEnabled()){
            logger.debug("start login.");
        }

        return "login";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start loginError.");
        }

        model.addAttribute("loginError", true);
        return "login";
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        if(logger.isDebugEnabled()){
            logger.debug("start passwordEncoder.");
        }

        return new BCryptPasswordEncoder();
    }
}
