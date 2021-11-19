package se.lexicon.service;

import se.lexicon.data.interfaces.BookingDAO;
import se.lexicon.data.interfaces.PremisesDAO;
import se.lexicon.exception.AppResourceNotFoundException;
import se.lexicon.model.Booking;
import se.lexicon.model.Premises;
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

    public void validate(PremisesForm form, boolean post){
        if(form != null){
            if(post && form.getId() != null){
                throw new IllegalArgumentException("form.id should be null");
            }
            if(!post && form.getId() == null){
                throw new IllegalArgumentException("form.id was null");
            }
            if(form.getName() == null || form.getName().isEmpty()){
                throw new IllegalArgumentException("form.name was null or empty");
            }
        }else {
            throw new IllegalStateException("Form was null");
        }
    }


    @Override
    public Premises create(PremisesForm form) {
        validate(form, true);
        Premises premises = new Premises(
                form.getName(),
                addressService.create(form.getAddress())
        );
        premises.setContactInfo(contactInfoService.create(form.getContactInfo()));

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
        validate(premisesForm, false);
        Premises premises = findById(id);
        premises.setName(premises.getName().trim());
        if(premisesForm.getAddress() != null){
            premises.setAddress(addressService.update(premisesForm.getAddress()));
        }
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
