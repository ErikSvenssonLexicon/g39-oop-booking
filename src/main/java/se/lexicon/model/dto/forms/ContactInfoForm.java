package se.lexicon.model.dto.forms;

import java.io.Serializable;

public class ContactInfoForm implements Serializable {

    private String id;
    private String email;
    private String phone;

    public ContactInfoForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
