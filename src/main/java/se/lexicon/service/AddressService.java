package se.lexicon.service;

import se.lexicon.model.Address;
import se.lexicon.model.dto.forms.AddressForm;

import java.util.List;

public interface AddressService {
    Address create(AddressForm form);
    Address findById(String id);
    List<Address> findAll();
    List<Address> findByStreetAddress(String streetAddress);
    List<Address> findByZipCode(String zipCode);
    List<Address> findByCity(String city);
    Address update(AddressForm addressForm);
    boolean delete(String id);
}
