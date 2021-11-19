package se.lexicon.model.dto.forms;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class AddressForm implements Serializable {
    private String id;
    @NotBlank(message = "This field is mandatory")
    private String streetAddress;
    @NotBlank(message = "This field is mandatory")
    private String zipCode;
    @NotBlank(message = "This field is mandatory")
    private String city;

    public AddressForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
