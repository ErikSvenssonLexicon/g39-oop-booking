package se.lexicon.service;

import se.lexicon.data.interfaces.ContactInfoDAO;
import se.lexicon.data.interfaces.PatientDAO;
import se.lexicon.exception.AppResourceNotFoundException;
import se.lexicon.model.ContactInfo;
import se.lexicon.model.Patient;
import se.lexicon.model.dto.forms.PatientForm;

import java.util.List;

public class PatientServiceImpl implements PatientService{

    private final PatientDAO patientDAO;
    private final ContactInfoDAO contactInfoDAO;
    private final UserCredentialsService userCredentialsService;

    public PatientServiceImpl(PatientDAO patientDAO, ContactInfoDAO contactInfoDAO, UserCredentialsService userCredentialsService) {
        this.patientDAO = patientDAO;
        this.contactInfoDAO = contactInfoDAO;
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
                contactInfoDAO.create(new ContactInfo(form.getContactInfo().getEmail(), form.getContactInfo().getPhone()))
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
    public Patient update(String id, PatientForm dto) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
