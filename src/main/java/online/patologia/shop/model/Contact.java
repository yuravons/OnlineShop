package online.patologia.shop.model;

import javax.persistence.*;

@Entity
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String number;

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return  this.name;
    }

    public void setNumber(String name) {
        this.number = number;
    }

    public String getNumber() {
        return  this.number;
    }

    public void addContactToDB() {

    }
}
