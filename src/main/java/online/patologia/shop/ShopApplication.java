package online.patologia.shop;

import online.patologia.shop.model.Product;
import online.patologia.shop.model.Provider;
import online.patologia.shop.repo.ProductRepo;
import online.patologia.shop.repo.ProviderRepo;
import online.patologia.shop.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(ProductRepo productRepo, UserRepo userRepo, ProviderRepo providerRepo){
		return args -> {
			providerRepo.save(new Provider("Allo", "Ukraine", 1000, "https://www.iguides.ru/upload/iblock/eb6/eb6e1820a0b9398310a0d489de27b154.jpg"));
			productRepo.save(new Product("Apple","Iphone 7",700.00,"https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201606271147",15, "PHONE"));
			productRepo.save(new Product("Apple","Iphone 8",900.00,"https://stat-m5.ms-online.pl/media/cache/gallery/rc/qtvl4toz/images/20/20090187/microsoft-nokia-230-czarny-1.jpg",15,"PHONE"));
		};
	}
}
