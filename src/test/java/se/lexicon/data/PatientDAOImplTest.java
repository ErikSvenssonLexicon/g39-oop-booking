package se.lexicon.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.model.ContactInfo;
import se.lexicon.model.Patient;
import se.lexicon.model.UserCredentials;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PatientDAOImplTest {

    public static final String SSN = "198010115631";
    public static final String FIRST_NAME = "Nisse";
    public static final String LAST_NAME = "Nilsson";
    public static final LocalDate BIRTH_DATE = LocalDate.parse("1980-10-11");
    public static final UserCredentials USER_CREDENTIALS = new UserCredentials("nisse", "nisse", "ROLE_USER");
    public static final ContactInfo CONTACT_INFO = new ContactInfo("nisse@gmail.com", "0701234567");
    private PatientDAOImpl testObject;


    public List<Patient> patients = new ArrayList<>(Arrays.asList(
            new Patient(SSN, FIRST_NAME, LAST_NAME, BIRTH_DATE, USER_CREDENTIALS, CONTACT_INFO),
            new Patient("199010112352", "Anna", "Andersson", LocalDate.parse("1990-10-11"), new UserCredentials("anna", "anna", "ROLE_ADMIN"), new ContactInfo("anna@gmail.com", "0731234456")),
            new Patient("198010125732", "Anna-Bella", "Olsson", LocalDate.parse("1980-10-12"), new UserCredentials("anna-bella", "anna-bella", "ROLE_USER"), new ContactInfo("bella@gmail.com", "076353246")),
            new Patient("198010145939", "Nils", "Svensson", LocalDate.parse("1980-10-14"), new UserCredentials("nisse2", "nisse2", "ROLE_USER"), new ContactInfo("nisse.svensson@gmail.com", "0701234567"))
    ));

    @BeforeEach
    void setUp() {
        testObject = PatientDAOImpl.getTestInstance(patients);
    }

    @Test
    void create() {
        Patient patient = new Patient("199909093526", "Ulf", "Bengtsson", LocalDate.parse("1999-09-09"), new UserCredentials("uffe", "uffe", "ROLE_ADMIN"), new ContactInfo("ulf@gmail.com", "071343542"));

        Patient result = testObject.create(patient);
        assertEquals(patient, result);
        assertTrue(testObject.findById(patient.getId()).isPresent());
    }

    @Test
    @DisplayName("findAll return 4 objects")
    void findAll() {
        assertEquals(4, testObject.findAll().size());
    }

    @Test
    void findById() {
        Optional<Patient> result = testObject.findById(patients.get(0).getId());
        assertTrue(result.isPresent());
        assertEquals(patients.get(0), result.get());
    }

    @Test
    void delete() {
        String id = patients.get(0).getId();
        assertTrue(testObject.delete(id));
        assertFalse(testObject.findById(id).isPresent());
    }

    @Test
    void findBySSN() {
        assertTrue(testObject.findBySSN(SSN).isPresent());
    }

    @Test
    void findByName() {
        int expectedSize = 2;
        String name = "anna";

        List<Patient> result = testObject.findByName(name);

        assertEquals(expectedSize, result.size());
    }

    @Test
    void findByUsername() {
        assertTrue(testObject.findByUsername("nisse").isPresent());
    }

    @Test
    void findByEmail() {
        assertTrue(testObject.findByEmail("nisse@gmail.com").isPresent());
    }
}