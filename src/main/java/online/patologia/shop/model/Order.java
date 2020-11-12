package online.patologia.shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @OneToMany
    private List<Item> items;
    @Embedded
    @OneToOne
    private AddressAndPersonalData addressAndPersonalData;
    private Double price;
    private boolean send;

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public Order() {
    }

    public Order(@NotNull List<Item> items, @NotNull AddressAndPersonalData addressAndPersonalData, Double price,boolean send) {
        this.items = items;
        this.addressAndPersonalData = addressAndPersonalData;
        this.price = price;
        this.send=send;
    }

    public Order(@NotNull List<Item> items, @NotNull AddressAndPersonalData addressAndPersonalData, Double price) {
        this.items = items;
        this.addressAndPersonalData = addressAndPersonalData;
        this.price = price;
        this.send=false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public AddressAndPersonalData getAddressAndPersonalData() {
        return addressAndPersonalData;
    }

    public void setAddressAndPersonalData(AddressAndPersonalData addressAndPersonalData) {
        this.addressAndPersonalData = addressAndPersonalData;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
