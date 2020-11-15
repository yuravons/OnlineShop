package online.patologia.shop.model;

import javax.persistence.*;

@Entity
@Table(name="shipments")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
}
