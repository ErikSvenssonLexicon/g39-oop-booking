package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    public static final String SSN = "8409111424";
    public static final String FIRST_NAME = "Nils";
    public static final String LAST_NAME = "Svensson";
    public static final LocalDate BIRTH_DATE = LocalDate.parse("1984-09-11");
    private Patient testObject;
    private ContactInfo contactInfo;
    private UserCredentials userCredentials;

    @BeforeEach
    public void setUp() {
        contactInfo = new ContactInfo("nisse@gmail.com", "3425262");
        userCredentials = new UserCredentials("nisse", "nisse", "ROLE_USER");
        testObject = new Patient(
                SSN,
                FIRST_NAME, LAST_NAME,
                BIRTH_DATE,
                userCredentials,
                contactInfo
        );
    }

    @Test
    public void testObject_successfully_instantiated(){
        assertNotNull(testObject.getId());
        assertEquals(SSN, testObject.getSsn());
        assertEquals(FIRST_NAME, testObject.getFirstName());
        assertEquals(LAST_NAME, testObject.getLastName());
        assertEquals(BIRTH_DATE, testObject.getBirthDate());
        assertNotNull(testObject.getContactInfo());
        assertNotNull(testObject.getCredentials());
    }

    @Test
    public void constructor_throws_exception_on_null_id(){
        assertThrows(
                RuntimeException.class,
                () -> new Patient(null, SSN, FIRST_NAME, LAST_NAME, BIRTH_DATE, userCredentials, contactInfo)
        );
    }

    @Test
    public void setCredentials_null_throws_runtime_exception() {
        assertThrows(
                RuntimeException.class,
                () -> testObject.setCredentials(null)
        );
    }
}