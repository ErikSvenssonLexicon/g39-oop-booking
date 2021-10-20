package se.lexicon.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactInfoTest {

    public static final String EMAIL = "nisse@gmail.com";
    public static final String PHONE = "0914034032394";
    private ContactInfo testObject;

    @Before
    public void setUp() {
        testObject = new ContactInfo(
                EMAIL, PHONE
        );
    }

    @Test
    public void testObject_successfully_instantiated() {
        assertNotNull(testObject.getId());
        assertEquals(EMAIL, testObject.getEmail());
        assertEquals(PHONE, testObject.getPhone());
    }

    @Test(expected = RuntimeException.class)
    public void constructor_throws_runtime_exception_on_null_id() {
        new ContactInfo(null, EMAIL, PHONE);
    }
}