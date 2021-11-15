package se.lexicon.data;

import se.lexicon.data.interfaces.ContactInfoDAO;
import se.lexicon.model.ContactInfo;

import java.util.*;

public class ContactInfoDAOImpl implements ContactInfoDAO {

    private static final ContactInfoDAOImpl INSTANCE;

    static {
        INSTANCE = new ContactInfoDAOImpl(null);
    }

    public static ContactInfoDAO getInstance(){
        return INSTANCE;
    }

    static ContactInfoDAOImpl getTestInstance(Collection<ContactInfo> contactInfos){
        return new ContactInfoDAOImpl(contactInfos);
    }

    private final Set<ContactInfo> contactInfos;

    private ContactInfoDAOImpl(Collection<ContactInfo> infoCollection){
        this.contactInfos = infoCollection == null ? new HashSet<>() : new HashSet<>(infoCollection);
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
}
