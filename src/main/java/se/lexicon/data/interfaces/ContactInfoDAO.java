package se.lexicon.data.interfaces;

import se.lexicon.model.ContactInfo;

import java.util.Optional;

public interface ContactInfoDAO extends GenericCRUD<ContactInfo, String>{
    Optional<ContactInfo> findByEmail(String email);
}
