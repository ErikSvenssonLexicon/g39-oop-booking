package se.lexicon.model.dto.forms;

import java.io.Serializable;
import java.time.LocalDate;

public class PatientForm implements Serializable {

    private String id;
    private String ssn;
    private String firstName;
    private String lastName;
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
