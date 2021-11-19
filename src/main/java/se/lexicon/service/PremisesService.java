package se.lexicon.service;

import se.lexicon.model.Premises;
import se.lexicon.model.dto.forms.PremisesForm;

import java.util.List;

public interface PremisesService {
    Premises create(PremisesForm form);
    Premises findById(String id);
    List<Premises> findAll();
    List<Premises> findByName(String name);
    List<Premises> findByCity(String city);
    List<Premises> findByZipCode(String zipCode);
    Premises update(String id, PremisesForm premisesForm);
    boolean delete(String id);
}
