package se.lexicon.model.dto.forms;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class ContactInfoForm implements Serializable {

    private String id;
    @NotBlank(message = "This field is mandatory")
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
