package se.lexicon.service;

import se.lexicon.data.interfaces.BookingDAO;
import se.lexicon.data.interfaces.PatientDAO;
import se.lexicon.exception.AppResourceNotFoundException;
import se.lexicon.model.Booking;
import se.lexicon.model.Patient;
import se.lexicon.model.dto.forms.PatientForm;

import java.util.List;

public class PatientServiceImpl implements PatientService{

    private final PatientDAO patientDAO;
    private final ContactInfoService contactInfoService;
    private final BookingDAO bookingDAO;
    private final UserCredentialsService userCredentialsService;

    public PatientServiceImpl(PatientDAO patientDAO, ContactInfoService contactInfoService, BookingDAO bookingDAO, UserCredentialsService userCredentialsService) {
        this.patientDAO = patientDAO;
        this.contactInfoService = contactInfoService;
        this.bookingDAO = bookingDAO;
        this.userCredentialsService = userCredentialsService;
    }

    @Override
    public Patient create(PatientForm form) {
        if(form.getContactInfo() != null && patientDAO.findByEmail(form.getContactInfo().getEmail()).isPresent()){
            throw new IllegalArgumentException("Email " + form.getContactInfo().getEmail() + " is already taken");
        }

        Patient patient = new Patient(
                form.getSsn(),
                form.getFirstName(),
                form.getLastName(),
                form.getBirthDate(),
                userCredentialsService.create(form.getCredentials(), "ROLE_USER"),
                form.getContactInfo() == null ? null : contactInfoService.create(form.getContactInfo())
        );

        return patientDAO.create(patient);
    }

    @Override
    public Patient findById(String id) {
        return patientDAO.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find patient with id " + id));
    }

    @Override
    public Patient findByUsername(String username) {
        return patientDAO.findByUsername(username)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find patient with username " + username));
    }

    @Override
    public Patient findBySSN(String ssn) {
        return patientDAO.findBySSN(ssn)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find patient with ssn " + ssn));
    }

    @Override
    public Patient findByEmail(String email) {
        return patientDAO.findByEmail(email)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find patient with email " + email));
    }

    @Override
    public List<Patient> findAll() {
        return patientDAO.findAll();
    }

    @Override
    public List<Patient> findByName(String name) {
        return patientDAO.findByName(name);
    }

    @Override
    public Patient update(String id, PatientForm form) {
        Patient patient = findById(id);

        patient.setFirstName(form.getFirstName());
        patient.setLastName(form.getLastName());
        patient.setBirthDate(form.getBirthDate());
        patient.setSsn(form.getSsn());

        return patient;
    }

    @Override
    public boolean delete(String id) {
        Patient patient = findById(id);
        userCredentialsService.delete(patient.getCredentials().getId());
        contactInfoService.delete(patient.getContactInfo().getId());
        List<Booking> bookings = bookingDAO.findByPatientId(id);
        bookings.forEach(booking -> booking.setPatient(null));
        return patientDAO.delete(id);
    }
}
