package online.patologia.shop.controllers;

import online.patologia.shop.model.Contact;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactsController {
    final static Logger logger = Logger.getLogger(ContactsController.class);
    private Contact newUser;

    @GetMapping("/contact")
    public String getInformationAboutContacts(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start getInformationAboutContacts.");
        }

        //Temporary initializing user
        newUser = new Contact();
        String userName = newUser.getName();
        String userNumber = newUser.getNumber();

        return "contact";
    }

    @GetMapping("/contact/new")
    public void addNewContact(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start addNewContact.");
        }

        newUser = new Contact();
        newUser.setName("Yurii");
        newUser.setNumber("0876583222");
        newUser.addContactToDB();

    }

}
