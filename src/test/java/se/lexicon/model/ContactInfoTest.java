package se.lexicon.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactInfoTest {

    public static final String EMAIL = "nisse@gmail.com";
    public static final String PHONE = "0914034032394";
    private ContactInfo testObject;

    @BeforeEach
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

    @Test
    public void constructor_throws_runtime_exception_on_null_id() {
        assertThrows(
                RuntimeException.class,
                () -> new ContactInfo(null, EMAIL, PHONE)
        );
    }
}