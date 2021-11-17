package se.lexicon.service;

import se.lexicon.model.Patient;
import se.lexicon.model.dto.forms.PatientForm;

import java.util.List;

public interface PatientService {
    Patient create(PatientForm form);
    Patient findById(String id);
    Patient findByUsername(String username);
    Patient findBySSN(String ssn);
    Patient findByEmail(String email);
    List<Patient> findAll();
    List<Patient> findByName(String name);
    Patient update(String id, PatientForm form);
    boolean delete(String id);
}
