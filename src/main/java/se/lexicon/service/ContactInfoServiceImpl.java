package se.lexicon.service;

import se.lexicon.data.interfaces.ContactInfoDAO;
import se.lexicon.exception.AppResourceNotFoundException;
import se.lexicon.model.ContactInfo;
import se.lexicon.model.dto.forms.ContactInfoForm;

import java.util.List;
import java.util.Optional;

public class ContactInfoServiceImpl implements ContactInfoService{

    private final ContactInfoDAO contactInfoDAO;

    public ContactInfoServiceImpl(ContactInfoDAO contactInfoDAO) {
        this.contactInfoDAO = contactInfoDAO;
    }

    @Override
    public ContactInfo create(ContactInfoForm form) {
        if(form == null) throw new IllegalArgumentException("Form was null");
        if(form.getEmail() == null || form.getEmail().isEmpty()){
            throw new IllegalArgumentException("Email is not allowed to be null or empty");
        }
        if(contactInfoDAO.findByEmail(form.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email " + form.getEmail()+ " is already taken");
        }
        ContactInfo contactInfo = new ContactInfo(form.getEmail(), form.getPhone());
        return contactInfoDAO.create(contactInfo);
    }

    @Override
    public ContactInfo findById(String id) {
        return contactInfoDAO.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find ContactInfo with id " + id));
    }

    @Override
    public List<ContactInfo> findAll() {
        return contactInfoDAO.findAll();
    }

    @Override
    public ContactInfo update(String id, ContactInfoForm form) {
        if(form == null) throw new IllegalArgumentException("Form was null");
        if(form.getEmail() == null || form.getEmail().isEmpty()){
            throw new IllegalArgumentException("Email is not allowed to be null or empty");
        }
        ContactInfo contactInfo = findById(id);
        Optional<ContactInfo> optional = contactInfoDAO.findByEmail(form.getEmail());
        if(optional.isPresent() && !optional.get().getId().equals(contactInfo.getId())){
            throw new IllegalArgumentException("Email " + form.getEmail() +" is already taken");
        }

        contactInfo.setPhone(form.getPhone());
        contactInfo.setEmail(form.getEmail());

        return contactInfo;
    }

    @Override
    public boolean delete(String id) {
        return contactInfoDAO.delete(id);
    }
}
