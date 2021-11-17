package se.lexicon.data;

import se.lexicon.data.interfaces.ContactInfoDAO;
import se.lexicon.io.JSONManager;
import se.lexicon.model.ContactInfo;

import java.io.File;
import java.util.*;

import static se.lexicon.io.URLConstants.CONTACT_INFO_JSON;

public class ContactInfoDAOImpl implements ContactInfoDAO {

    private static ContactInfoDAOImpl INSTANCE;

    public static ContactInfoDAO getInstance(){
        if(INSTANCE == null) INSTANCE = new ContactInfoDAOImpl(null);
        return INSTANCE;
    }

    static ContactInfoDAOImpl getTestInstance(Collection<ContactInfo> contactInfos){
        if(contactInfos == null) contactInfos = new ArrayList<>();
        return new ContactInfoDAOImpl(contactInfos);
    }

    private final Set<ContactInfo> contactInfos;

    private ContactInfoDAOImpl(Collection<ContactInfo> infoCollection){
        this.contactInfos = infoCollection == null ? new HashSet<>(JSONManager.getInstance().deserializeFromJSON(new File(CONTACT_INFO_JSON), ContactInfo.class)) : new HashSet<>(infoCollection);
    }

    @Override
    public ContactInfo create(ContactInfo contactInfo) {
        if(contactInfo == null) throw new IllegalArgumentException("ContactInfo was null");
        if(contactInfo.getId() == null) throw new IllegalArgumentException("ContactInfo.id was null");
        contactInfos.add(contactInfo);
        return contactInfo;
    }

    @Override
    public List<ContactInfo> findAll() {
        return new ArrayList<>(contactInfos);
    }

    @Override
    public Optional<ContactInfo> findById(final String id) {
        return contactInfos.stream()
                .filter(contactInfo -> contactInfo.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean delete(String id) {
        return findById(id)
                .map(contactInfos::remove)
                .orElse(false);
    }

    @Override
    public Optional<ContactInfo> findByEmail(String email) {
        return contactInfos.stream()
                .filter(contactInfo -> contactInfo.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}
