package se.lexicon.model.dto.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BookingForm implements Serializable {
    private String id;
    @NotNull(message = "This field is mandatory")
    private LocalDateTime dateTime;
    @PositiveOrZero(message = "Need to be 0 or positive number")
    private double price;
    private String administrator;
    @NotBlank(message = "This field is required")
    private String vaccineId;
    private boolean vacant;
    @NotBlank(message = "This field is required")
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
