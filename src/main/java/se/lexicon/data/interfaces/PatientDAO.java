package se.lexicon.data.interfaces;

import se.lexicon.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDAO extends GenericCRUD<Patient, String>{

    Optional<Patient> findBySSN(String ssn);
    List<Patient> findByName(String name);
    Optional<Patient> findByUsername(String username);
    Optional<Patient> findByEmail(String email);

}
