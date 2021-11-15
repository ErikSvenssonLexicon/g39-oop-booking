package se.lexicon.data;

import se.lexicon.data.interfaces.PatientDAO;
import se.lexicon.model.Patient;

import java.util.*;
import java.util.stream.Collectors;

public class PatientDAOImpl implements PatientDAO {

    private static final PatientDAOImpl INSTANCE;

    static {
        INSTANCE = new PatientDAOImpl(null);
    }

    public static PatientDAOImpl getInstance(){
        return INSTANCE;
    }

    private PatientDAOImpl(Collection<Patient> patientCollection){
        this.patients = patientCollection == null ? new HashSet<>() : new HashSet<>(patientCollection);
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
                .filter(patient -> patient.getFirstName().toLowerCase().contains(name.toLowerCase()) || patient.getLastName().toLowerCase().contains(name.toLowerCase()))
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
