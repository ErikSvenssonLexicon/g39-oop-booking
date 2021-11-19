package se.lexicon.model.dto.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class UserCredentialsForm implements Serializable {

    private String id;
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 100, message = "Need to have between 2 and 100 characters")
    private String username;
    @NotBlank(message = "This field is mandatory")
    @Size(min = 7, message = "Need to have at minimum 7 characters")
    private String password;

    public UserCredentialsForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
