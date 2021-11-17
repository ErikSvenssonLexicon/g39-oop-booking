package se.lexicon.model.dto.forms;

import java.io.Serializable;

public class PremisesForm implements Serializable {

    private String id;
    private String name;
    private AddressForm address;
    private ContactInfoForm contactInfo;

    public PremisesForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressForm getAddress() {
        return address;
    }

    public void setAddress(AddressForm address) {
        this.address = address;
    }

    public ContactInfoForm getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfoForm contactInfo) {
        this.contactInfo = contactInfo;
    }


}
