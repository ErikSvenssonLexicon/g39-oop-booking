package se.lexicon.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PremisesTest {

    public static final String NAME = "Vårdcentral norr";
    private Premises testObject;
    private ContactInfo contactInfo;
    private Address address;

    @Before
    public void setUp() {
        contactInfo = new ContactInfo("nisse@gmail.com", "12435455");
        address = new Address("Storgatan 1", "35235", "Byhåla");
        testObject = new Premises(
                NAME,
                address
        );
        testObject.setContactInfo(contactInfo);
    }

    @Test
    public void testObject_successfully_instantiated() {
        assertNotNull(testObject.getId());
        assertEquals(NAME, testObject.getName());
        assertNotNull(testObject.getAddress());
        assertNotNull(testObject.getContactInfo());
    }

    @Test(expected = RuntimeException.class)
    public void constructor_throws_runtime_exception_on_null_id() {
        new Premises(null, NAME, address, contactInfo);
    }

    @Test(expected = RuntimeException.class)
    public void setAddress_throws_runtime_exception_on_null() {
        testObject.setAddress(null);
    }
}