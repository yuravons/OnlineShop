package online.patologia.shop.service;

import online.patologia.shop.model.Provider;
import online.patologia.shop.repo.ProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    @Autowired
    private ProviderRepo providerRepo;

    public Page<Provider> findAvailable(PageRequest pageRequest) {
        return providerRepo.findAvailable(pageRequest);
    }

    public Provider save(Provider provider) {
        return providerRepo.save(provider);
    }

    public List<Provider> findAll() {
        return providerRepo.findAll();
    }

    public void deleteById(Long id) {
        providerRepo.deleteById(id);
    }

    public Provider getOne(Long id) {
        return providerRepo.getOne(id);
    }

}
