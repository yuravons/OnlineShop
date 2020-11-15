package online.patologia.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactsController {

    @GetMapping("/contact")
    public String getInformationAboutContacts(Model model) {

        return "contact";
    }

}
