package online.patologia.shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class AddressAndPersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotEmpty(message = "Введіть ім'я")
    @Size(min = 2, message = "Назва занадто коротка")
    @Size(max = 30, message = "Назва занадто довга")
    private String firstName;
    @NotBlank
    @NotEmpty(message = "Введіть прізвище")
    @Size(min = 2, message = "Прізвище занадто коротке")
    @Size(max = 30, message = "Прізвище занадто довге")
    private String lastName;
    @NotBlank
    @NotEmpty(message = "Введіть місто")
    @Size(min = 2, message = "Назва міста занадто коротка")
    @Size(max = 30, message = "Назва міста занадто довга")
    private String city;
    @NotBlank
    @NotEmpty(message = "Введіть назву вулиці")
    @Size(min = 2, message = "Назва вулиці закоротка")
    @Size(max = 30, message = "Назва вулиці задовга")
    private String street;
    @NotBlank
    @NotEmpty(message = "Введіть індекс")
    @Size(min = 2, message = "Індекс закороткий")
    @Size(max = 30, message = "Індекс задовгий")
    private String zipCode;
    @NotBlank
    @NotEmpty(message = "Введіть номер")
    @Size(min = 2, message = "Номер закороткий")
    @Size(max = 30, message = "Номер задовгий")
    private String phoneNumber;

    public AddressAndPersonalData(String firstName, String lastName, String city, String street, String zipCode, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.phoneNumber=phoneNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressAndPersonalData() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipPost() {
        return zipCode;
    }

    public void setZipPost(String zipPost) {
        this.zipCode = zipPost;
    }
}
