package se.lexicon.model.dto.forms;

import se.lexicon.model.Premises;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BookingForm implements Serializable {
    private String id;
    private LocalDateTime dateTime;
    private double price;
    private String administrator;
    private String vaccineId;
    private boolean vacant;
    private String premisesId;

    public BookingForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public boolean isVacant() {
        return vacant;
    }

    public void setVacant(boolean vacant) {
        this.vacant = vacant;
    }

    public String getPremisesId() {
        return premisesId;
    }

    public void setPremisesId(String premisesId) {
        this.premisesId = premisesId;
    }
}
