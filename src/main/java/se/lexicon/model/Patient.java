package se.lexicon.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Patient implements Serializable {
    private String id;
    private String ssn;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private UserCredentials credentials;
    private ContactInfo contactInfo;

    public Patient(
            String id,
            String ssn,
            String firstName,
            String lastName,
            LocalDate birthDate,
            UserCredentials credentials,
            ContactInfo contactInfo
    ) {
        if(id == null){
            throw new RuntimeException("id was null");
        }
        this.id = id;
        setSsn(ssn);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setCredentials(credentials);
        setContactInfo(contactInfo);
    }

    public Patient(
            String ssn,
            String firstName,
            String lastName,
            LocalDate birthDate,
            UserCredentials credentials,
            ContactInfo contactInfo) {
        this(
                UUID.randomUUID().toString(),
                ssn,
                firstName,
                lastName,
                birthDate,
                credentials,
                contactInfo
        );
    }

    Patient() {
    }

    public String getId() {
        return id;
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

    public UserCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(UserCredentials credentials) {
        if(credentials == null){
            throw new RuntimeException("UserCredentials was null");
        }
        this.credentials = credentials;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
