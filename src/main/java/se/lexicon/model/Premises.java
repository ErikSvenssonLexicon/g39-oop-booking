package se.lexicon.model;

import java.io.Serializable;
import java.util.UUID;

public class Premises implements Serializable {

    private String id;
    private String name;
    private Address address;
    private ContactInfo contactInfo;

    public Premises(String id, String name, Address address, ContactInfo contactInfo) {
        if(id == null){
            throw new RuntimeException("id was null");
        }
        this.id = id;
        setName(name);
        setAddress(address);
        setContactInfo(contactInfo);
    }

    public Premises(String name, Address address) {
        this(UUID.randomUUID().toString(), name, address, null);
    }

    Premises(){}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if(address == null){
            throw new RuntimeException("Address was null");
        }
        this.address = address;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
