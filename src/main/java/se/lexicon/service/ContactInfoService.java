package se.lexicon.service;

import se.lexicon.model.ContactInfo;
import se.lexicon.model.dto.forms.ContactInfoForm;

import java.util.List;

public interface ContactInfoService {
    ContactInfo create(ContactInfoForm form);
    ContactInfo findById(String id);
    List<ContactInfo> findAll();
    ContactInfo update(String id, ContactInfoForm form);
    boolean delete(String id);
}
