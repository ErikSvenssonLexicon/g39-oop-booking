package se.lexicon.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import se.lexicon.model.ContactInfo;

import java.time.LocalDate;
import java.util.List;

public class PatientDTO {

    private String id;
    private String ssn;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookingDTO> bookings;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserCredentialsDTO credentials;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ContactInfo contactInfo;

    public PatientDTO() {
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

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }

    public UserCredentialsDTO getCredentials() {
        return credentials;
    }

    public void setCredentials(UserCredentialsDTO credentials) {
        this.credentials = credentials;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id='" + id + '\'' +
                ", ssn='" + ssn + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", bookings=" + bookings +
                ", credentials=" + credentials +
                ", contactInfo=" + contactInfo +
                '}';
    }
}
