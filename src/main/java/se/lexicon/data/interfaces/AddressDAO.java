package se.lexicon.data.interfaces;

import se.lexicon.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDAO extends GenericCRUD<Address, String>{
    List<Address> findByStreetAddress(String streetAddress);
    List<Address> findByZipCode(String zipCode);
    List<Address> findByCity(String city);
    long countReferencesToAddressId(String id);
    Optional<Address> findByStreetZipCodeAndCity(String street, String zipCode, String city);
}
