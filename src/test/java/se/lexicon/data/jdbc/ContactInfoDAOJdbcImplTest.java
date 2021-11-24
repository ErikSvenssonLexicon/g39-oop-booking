package se.lexicon.data.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.H2Util;
import se.lexicon.data.jdbc.ContactInfoDAOJdbcImpl;
import se.lexicon.model.ContactInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ContactInfoDAOJdbcImplTest {

    public static final String ID = "id1";
    public static final String EMAIL = "test1@gmail.com";
    public static final String PHONE = null;
    private static H2Util h2Util;

    @BeforeAll
    static void beforeAll() {
        h2Util = H2Util.getInstance();
    }

    public List<ContactInfo> contactInfos(){
        List<ContactInfo> contactInfos = new ArrayList<>();
        contactInfos.add(new ContactInfo(ID, EMAIL, PHONE));
        contactInfos.add(new ContactInfo("id2", "test2@gmail.com", "0701234567"));
        return contactInfos;
    }

    private final ContactInfoDAOJdbcImpl testObject = new ContactInfoDAOJdbcImpl();
    private ContactInfo contactInfo;

    @BeforeEach
    void setUp() throws Exception {
        h2Util.dropAndCreateTables();
        List<ContactInfo> persistedContactInfos = contactInfos().stream()
                .map(testObject::create)
                .collect(Collectors.toList());
        contactInfo = persistedContactInfos.get(1);
    }

    @Test
    void findByEmail() {
        assertTrue(testObject.findByEmail(EMAIL).isPresent());
    }

    @Test
    void create() {
        String email = "test3@gmail.com";
        String phone = "0739876543";
        ContactInfo contactInfo = new ContactInfo(email, phone);

        testObject.create(contactInfo);

        assertTrue(testObject.findById(contactInfo.getId()).isPresent());
    }

    @Test
    void findAll() {
        int expectedSize = 2;
        List<ContactInfo> result = testObject.findAll();
        assertEquals(expectedSize, result.size());
    }

    @Test
    void findById() {
        assertTrue(testObject.findById(ID).isPresent());
    }

    @Test
    void delete() {
        assertTrue(testObject.delete(ID));
        assertFalse(testObject.findById(ID).isPresent());
    }

    @Test
    void update() {
        String email = "test4@gmail.com";
        contactInfo.setEmail(email);
        String phone = "0706588379";
        contactInfo.setPhone(phone);

        ContactInfo result = testObject.update(contactInfo);
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(phone, result.getPhone());
    }
}