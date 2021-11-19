package se.lexicon.service;

import se.lexicon.data.interfaces.BookingDAO;
import se.lexicon.data.interfaces.PremisesDAO;
import se.lexicon.exception.AppResourceNotFoundException;
import se.lexicon.model.Booking;
import se.lexicon.model.Premises;
import se.lexicon.model.dto.forms.AddressForm;
import se.lexicon.model.dto.forms.ContactInfoForm;
import se.lexicon.model.dto.forms.PremisesForm;

import java.util.List;

public class PremisesServiceImpl implements  PremisesService{

    private final PremisesDAO premisesDAO;
    private final AddressService addressService;
    private final ContactInfoService contactInfoService;
    private final BookingDAO bookingDAO;

    public PremisesServiceImpl(PremisesDAO premisesDAO, AddressService addressService, ContactInfoService contactInfoService, BookingDAO bookingDAO) {
        this.premisesDAO = premisesDAO;
        this.addressService = addressService;
        this.contactInfoService = contactInfoService;
        this.bookingDAO = bookingDAO;
    }

    @Override
    public Premises create(PremisesForm form) {
        if(form == null) throw new IllegalArgumentException("Form was null");
        if(form.getAddress() == null) throw new IllegalArgumentException("Form.address was null");
        FormValidator formValidator = FormValidator.getInstance();

        formValidator.validate(form, PremisesForm.class);
        formValidator.validate(form.getAddress(), AddressForm.class);

        if(form.getContactInfo() != null){
            formValidator.validate(form.getContactInfo(), ContactInfoForm.class);
        }

        Premises premises = new Premises(
                form.getName(),
                addressService.create(form.getAddress()),
                form.getContactInfo() == null ? null : contactInfoService.create(form.getContactInfo())
        );


        return premisesDAO.create(premises);
    }

    @Override
    public Premises findById(String id) {
        return premisesDAO.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find Premises with id " + id));
    }

    @Override
    public List<Premises> findAll() {
        return premisesDAO.findAll();
    }

    @Override
    public List<Premises> findByName(String name) {
        return premisesDAO.findByName(name);
    }

    @Override
    public List<Premises> findByCity(String city) {
        return premisesDAO.findByCity(city);
    }

    @Override
    public List<Premises> findByZipCode(String zipCode) {
        return premisesDAO.findByZipCode(zipCode);
    }

    @Override
    public Premises update(String id, PremisesForm premisesForm) {
        if(id == null) throw new IllegalArgumentException("Id was null");
        if(premisesForm == null) throw new IllegalArgumentException("Form was null");
        FormValidator.getInstance().validate(premisesForm, PremisesForm.class);
        Premises premises = findById(id);
        premises.setName(premisesForm.getName().trim());
        return premises;
    }

    @Override
    public boolean delete(String id) {
        Premises premises = findById(id);

        List<Booking> bookings = bookingDAO.findByPremisesId(id);
        bookings.forEach(booking -> booking.setPremises(null));

        if(premises.getContactInfo() != null){
            contactInfoService.delete(premises.getContactInfo().getId());
        }

        return premisesDAO.delete(id);
    }
}
