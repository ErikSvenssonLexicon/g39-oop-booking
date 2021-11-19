package se.lexicon.service;

import se.lexicon.data.interfaces.AddressDAO;
import se.lexicon.data.interfaces.PremisesDAO;
import se.lexicon.exception.AppResourceNotFoundException;
import se.lexicon.model.Address;
import se.lexicon.model.dto.forms.AddressForm;

import java.util.List;

public class AddressServiceImpl implements AddressService{

    private final AddressDAO addressDAO;

    public AddressServiceImpl(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }


    @Override
    public Address create(AddressForm form) {
        if(form == null) throw new IllegalArgumentException("Form was null");
        FormValidator.getInstance().validate(form, AddressForm.class);

        return addressDAO.findAll().stream()
                .filter(
                        address -> address.getStreetAddress().equalsIgnoreCase(form.getStreetAddress())
                                && address.getZipCode().equalsIgnoreCase(form.getZipCode())
                                && address.getCity().equalsIgnoreCase(form.getCity()))
                .findFirst()
                .orElse(addressDAO.create(new Address(form.getStreetAddress(), form.getZipCode(), form.getCity())));
    }

    @Override
    public Address findById(String id) {
        return addressDAO.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find address with id " + id));
    }

    @Override
    public List<Address> findAll() {
        return addressDAO.findAll();
    }

    @Override
    public List<Address> findByStreetAddress(String streetAddress) {
        return addressDAO.findByStreetAddress(streetAddress);
    }

    @Override
    public List<Address> findByZipCode(String zipCode) {
        return addressDAO.findByZipCode(zipCode);
    }

    @Override
    public List<Address> findByCity(String city) {
        return addressDAO.findByCity(city);
    }

    @Override
    public Address update(AddressForm addressForm) {
        return create(addressForm);
    }

    @Override
    public boolean delete(String id) {
        return addressDAO.delete(id);
    }
}
