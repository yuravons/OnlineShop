package online.patologia.shop.model;


public class CartTest {
    private Long id;
    private Long user_id;
    private Long product_id;
    private int quantity;
    private String name;
    private Double price;
    private String producer;
    private String imageSource;



    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public CartTest(Long id,Long user_id, Long product_id, int quantity, String name, Double price, String producer, String imageSource) {
        this.id=id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.producer = producer;
        this.imageSource=imageSource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartTest() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
