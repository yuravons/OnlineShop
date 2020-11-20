package online.patologia.shop.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedbackController {
    final static Logger logger = Logger.getLogger(FeedbackController.class);

    @GetMapping("/feedback")
    public String getPageToWriteFeedback(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start getPageToWriteFeedback.");
        }

        return "feedback";
    }
}
