package se.lexicon.model;

import java.io.Serializable;
import java.util.UUID;

public class ContactInfo implements Serializable {

    private String id;
    private String email;
    private String phone;

    public ContactInfo(String id, String email, String phone) {
        if(id == null){
            throw new RuntimeException("id was null");
        }
        this.id = id;
        setEmail(email);
        setPhone(phone);
    }

    public ContactInfo(String email, String phone) {
        this(UUID.randomUUID().toString(), email, phone);
    }

    ContactInfo() {
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
