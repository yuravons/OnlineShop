package online.patologia.shop.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderShipmentController {
    final static Logger logger = Logger.getLogger(OrderShipmentController.class);

    @GetMapping("/ordershipment")
    public String getOrderShipmentPage(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start getOrderShipmentPage.");
        }

        return "ordershipment";
    }
}
