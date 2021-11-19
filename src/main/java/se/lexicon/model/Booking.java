package se.lexicon.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Booking implements Serializable {

    private String id;
    private LocalDateTime dateTime;
    private double price;
    private String administrator;
    private String vaccineId;
    private boolean vacant;
    private Premises premises;
    private Patient patient;

    public Booking(String id, LocalDateTime dateTime, double price, String administrator, String vaccineId, Premises premises, Patient patient, boolean vacant) {
        if(id == null){
            throw new RuntimeException("id was null");
        }
        this.id = id;
        setDateTime(dateTime);
        setPrice(price);
        setAdministrator(administrator);
        setVaccineId(vaccineId);
        setVacant(vacant);
        setPremises(premises);
        setPatient(patient);
    }

    public Booking(LocalDateTime dateTime, double price, String vaccineId, Premises premises) {
        this(UUID.randomUUID().toString(), dateTime, price, null, vaccineId, premises, null, true);
    }

    Booking() {
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        if(dateTime == null) throw new IllegalArgumentException("Parameter dateTime was null");
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
        if(vaccineId == null) throw new IllegalArgumentException("Parameter: String vaccineId was null");
        this.vaccineId = vaccineId;
    }

    public boolean isVacant() {
        return vacant;
    }

    private void setVacant(boolean vacant) {
        this.vacant = vacant;
    }

    public Premises getPremises() {
        return premises;
    }

    public void setPremises(Premises premises) {
        this.premises = premises;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        setVacant(this.patient == null);
    }
}
