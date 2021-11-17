package se.lexicon.model.dto.views;

import com.fasterxml.jackson.annotation.JsonInclude;
import se.lexicon.model.Address;
import se.lexicon.model.ContactInfo;

import java.util.List;

public class PremisesDTO {
    private String id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Address address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ContactInfo contactInfo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookingDTO> bookings;

    public PremisesDTO() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "PremisesDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", contactInfo=" + contactInfo +
                ", bookings=" + bookings +
                '}';
    }
}
