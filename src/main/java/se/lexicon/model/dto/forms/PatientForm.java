package se.lexicon.model.dto.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

public class PatientForm implements Serializable {

    private String id;
    @NotBlank(message = "This field is mandatory")
    @Pattern(regexp = "^\\d{12}", message = "Invalid format. Should be provided in this format YYYYMMDDXXXX all digits")
    private String ssn;
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, message = "Need to have at least 2 characters")
    private String firstName;
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, message = "Need to have at least 2 characters")
    private String lastName;
    @NotNull(message = "This field is mandatory")
    private LocalDate birthDate;
    private UserCredentialsForm credentials;
    private ContactInfoForm contactInfo;

    public PatientForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UserCredentialsForm getCredentials() {
        return credentials;
    }

    public void setCredentials(UserCredentialsForm credentials) {
        this.credentials = credentials;
    }

    public ContactInfoForm getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfoForm contactInfo) {
        this.contactInfo = contactInfo;
    }
}
