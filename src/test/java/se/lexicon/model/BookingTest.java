package se.lexicon.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    public static final LocalDateTime DATE_TIME = LocalDateTime.parse("2022-01-07T13:00");
    public static final double PRICE = 400;
    public static final String VACCINE_ID = "Season flu";
    private Booking testObject;


    @BeforeEach
    public void setUp() {
        Premises premises = new Premises("Vårdcentral södra", new Address("Södergatan 3", "45263", "Byhåla"));
        testObject = new Booking(
                DATE_TIME,
                PRICE,
                VACCINE_ID,
                premises
        );
    }

    @Test
    public void testObject_successfully_instantiated() {
        assertNotNull(testObject);
        assertNotNull(testObject.getId());
        assertEquals(DATE_TIME, testObject.getDateTime());
        assertNull(testObject.getAdministrator());
        assertEquals(VACCINE_ID, testObject.getVaccineId());
        assertEquals(PRICE, testObject.getPrice(), 0);
        assertTrue(testObject.isVacant());
        assertNotNull(testObject.getPremises());
        assertNull(testObject.getPatient());
    }

    @Test
    public void given_patient_setPatient_will_update_vacant_to_false() {
        Patient patient = new Patient(
                null, null, null, null, new UserCredentials("", "", ""), null
        );

        testObject.setPatient(patient);

        assertNotNull(testObject.getPatient());
        assertFalse(testObject.isVacant());
    }
}