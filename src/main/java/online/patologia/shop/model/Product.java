package online.patologia.shop.model;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @NotEmpty(message = "Ім'я виробника не може бути пусте")
    @NotBlank
    @Size(min = 2, message = "Назва виробника занадто коротка")
    private String producer;
    @NotEmpty(message = "Назва виробника не може бути пуста")
    @NotBlank
    @Size(min = 2,message = "Назва продукту занадто коротка")
    private String name;
    @DecimalMax(value = "1000000.0", message = "Занадто висока ціна")
    @DecimalMin(value="0.0", message = "Ціна не може бути нижче 0")
    @NotNull(message = "Будь ласка, вкажіть ціну")
    private Double price;
    @NotEmpty(message = "Посилання на фотографію не може бути порожнім")
    @NotBlank
    @Size(min = 2,message = "Занадто коротке посилання на фото")
    private String imageSource;
    @NotNull(message = "Введіть кількість доступних товарів")
    @DecimalMin(value="0.0", message = "Кількість не може бути нижчою за 0")
    private Integer stock;
    private String category;

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public Product(@NotEmpty(message = "Ім'я виробника не може бути порожнім") @NotBlank @Size(min = 2, message = "Назва виробника занадто коротка") String producer, @NotEmpty(message = "Назва товару не може бути порожньою") @NotBlank @Size(min = 2, message = "Назва продукту занадто коротка") String name, @DecimalMax(value = "1000000.0", message = "Занадто висока ціна") @DecimalMin(value = "0.0", message = "Ціна не може бути нижче 0") @NotNull(message = "Будь ласка, вкажіть ціну") Double price, @NotEmpty(message = "Посилання на фотографію не може бути порожнім") @NotBlank @Size(min = 2, message = "Занадто коротке посилання на фото") String imageSource, @NotNull(message = "Введіть кількість доступних товарів") @DecimalMin(value = "0.0", message = "Кількість не може бути нижчою за 0") Integer stock, String category) {
        this.producer = producer;
        this.name = name;
        this.price = price;
        this.imageSource = imageSource;
        this.stock = stock;
        this.category = category;
    }

    public Product(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
