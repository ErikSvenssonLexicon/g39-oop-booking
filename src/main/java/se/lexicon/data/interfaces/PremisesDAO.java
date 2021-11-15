package se.lexicon.data.interfaces;

import se.lexicon.model.Premises;

import java.util.List;

public interface PremisesDAO extends GenericCRUD<Premises, String>{

    List<Premises> findByName(String name);

    List<Premises> findByCity(String city);

    List<Premises> findByZipCode(String zipCode);

}
