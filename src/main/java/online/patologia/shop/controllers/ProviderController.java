package online.patologia.shop.controllers;


import online.patologia.shop.model.Provider;
import online.patologia.shop.service.ProviderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProviderController {

    final static Logger logger = Logger.getLogger(ProviderController.class);

    @Autowired
    private ProviderService providerService;

    @GetMapping("/providers")
    public String getListProviders(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start getListProviders.");
        }

        model.addAttribute("provider", new Provider());
        List<Provider> providers = new ArrayList<>();
        providerService.findAll().forEach(provider -> providers.add(provider));
        model.addAttribute("providers",providers);
        return "providers";
    }

    @GetMapping("/providers/new")
    public void addNewProvider(Model model) {
        if(logger.isDebugEnabled()){
            logger.debug("start addNewProvider.");
        }

        model.addAttribute("provider", new Provider());
    }

}
