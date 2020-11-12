package online.patologia.shop.service;

import online.patologia.shop.model.AddressAndPersonalData;
import online.patologia.shop.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;

    public AddressAndPersonalData save(AddressAndPersonalData addressAndPersonalData) {
        return addressRepo.save(addressAndPersonalData);
    }
}
