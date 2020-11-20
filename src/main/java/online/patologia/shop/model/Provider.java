package online.patologia.shop.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private Long id;
    @NotEmpty(message = ".....")
    @NotBlank
    @Size(min = 2,message = ".....")
    private String name;
    @NotEmpty(message = ".....")
    @NotBlank
    @Size(min = 2,message = ".....")
    private String region;
    @NotEmpty(message = "Посилання на фотографію не може бути порожнім")
    @NotBlank
    @Size(min = 2,message = "Занадто коротке посилання на фото")
    private String imageSource;
    @NotNull(message = "....")
    @DecimalMin(value="0.0", message = "....")
    private Integer price;

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public Provider(@NotEmpty(message = "") @NotBlank @Size(min = 2, message = "") String name,
                    @NotEmpty(message = "") @NotBlank @Size(min = 2, message = "") String region,
                    @DecimalMax(value = "1000000.0", message = "") @DecimalMin(value = "0.0", message = "") @NotNull(message = "") Integer price,
                    @NotEmpty(message = "Посилання на фотографію не може бути порожнім") @NotBlank @Size(min = 2, message = "") String imageSource) {
        this.name = name;
        this.region = region;
        this.price = price;
        this.imageSource = imageSource;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Provider() {
    }

    public Long getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
