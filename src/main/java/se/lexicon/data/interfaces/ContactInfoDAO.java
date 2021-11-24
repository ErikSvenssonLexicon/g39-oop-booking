package se.lexicon.data.interfaces;

import se.lexicon.model.ContactInfo;

import java.util.Optional;

public interface ContactInfoDAO extends Update<ContactInfo> ,GenericCRUD<ContactInfo, String>{
    Optional<ContactInfo> findByEmail(String email);
}
