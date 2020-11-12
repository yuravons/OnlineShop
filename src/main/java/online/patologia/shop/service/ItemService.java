package online.patologia.shop.service;

import online.patologia.shop.model.Item;
import online.patologia.shop.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepo itemRepo;

    public Item save(Item item) {
        return itemRepo.save(item);
    }


}
