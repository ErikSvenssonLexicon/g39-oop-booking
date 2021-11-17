package se.lexicon.model.dto.views;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public class BookingDTO {

    private String id;
    private LocalDateTime dateTime;
    private double price;
    private String vaccineId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String administrator;
    private boolean vacant;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PremisesDTO premises;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PatientDTO patient;

    public BookingDTO() {
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

    public PremisesDTO getPremises() {
        return premises;
    }

    public void setPremises(PremisesDTO premises) {
        this.premises = premises;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", price=" + price +
                ", vaccineId='" + vaccineId + '\'' +
                ", administrator='" + administrator + '\'' +
                ", vacant=" + vacant +
                ", premises=" + premises +
                ", patient=" + patient +
                '}';
    }
}
