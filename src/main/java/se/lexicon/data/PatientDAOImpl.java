package se.lexicon.data;

import se.lexicon.data.interfaces.PatientDAO;
import se.lexicon.io.JSONManager;
import se.lexicon.model.Patient;
import se.lexicon.model.Premises;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static se.lexicon.io.URLConstants.PATIENTS_JSON;

public class PatientDAOImpl implements PatientDAO {

    private static PatientDAOImpl INSTANCE;

    public static PatientDAOImpl getInstance(){
        if(INSTANCE == null) INSTANCE = new PatientDAOImpl(null);
        return INSTANCE;
    }

    static PatientDAOImpl getTestInstance(Collection<Patient> patients){
        if(patients == null) patients = new ArrayList<>();
        return new PatientDAOImpl(patients);
    }

    private PatientDAOImpl(Collection<Patient> patientCollection){
        this.patients = patientCollection == null ? new HashSet<>(JSONManager.getInstance().deserializeFromJSON(new File(PATIENTS_JSON), Patient.class)) : new HashSet<>(patientCollection);
    }

    private final Set<Patient> patients;

    @Override
    public Patient create(Patient patient) {
        patients.add(patient);
        return patient;
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(patients);
    }

    @Override
    public Optional<Patient> findById(String id) {
        return patients.stream()
                .filter(patient -> patient.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean delete(String id) {
        Optional<Patient> optionalPatient = findById(id);
        return optionalPatient.map(patients::remove).orElse(false);

    }

    @Override
    public Optional<Patient> findBySSN(String ssn) {
        return patients.stream()
                .filter(patient -> patient.getSsn().equals(ssn))
                .findFirst();
    }

    @Override
    public List<Patient> findByName(String name) {
        return patients.stream()
                .filter(patient -> (patient.getFirstName() + " " + patient.getLastName()).toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Patient> findByUsername(String username) {
        return patients.stream()
                .filter(patient -> patient.getCredentials().getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<Patient> findByEmail(String email) {
        return patients.stream()
                .filter(patient -> patient.getContactInfo().getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}
