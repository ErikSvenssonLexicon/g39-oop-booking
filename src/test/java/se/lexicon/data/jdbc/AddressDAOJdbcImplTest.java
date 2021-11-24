package se.lexicon.data.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.H2Util;
import se.lexicon.data.DatabaseCredentials;
import se.lexicon.model.Address;
import se.lexicon.model.Premises;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddressDAOJdbcImplTest {

    public static final String ID = "id1";
    public static final String STREET_ADDRESS = "Storgatan 3";
    public static final String ZIP_CODE = "12345";
    public static final String CITY = "Växjö";
    private static H2Util h2Util;

    @BeforeAll
    static void beforeAll() {
        h2Util = H2Util.getInstance();
    }

    public List<Address> addresses(){
        return Arrays.asList(
                new Address(ID, STREET_ADDRESS, ZIP_CODE, CITY),
                new Address("id2", "Storgatan 4", "12345", "Växjö"),
                new Address("id3", "Storgatan 3", "54321", "Alvesta")
        );
    }

    private final AddressDAOJdbcImpl testObject = new AddressDAOJdbcImpl();

    @BeforeEach
    void setUp() throws Exception{
        h2Util.dropAndCreateTables();
        addresses().forEach(testObject::create);
    }

    @Test
    void findByStreetAddress() {
        int expected = 2;
        List<Address> result = testObject.findByStreetAddress(STREET_ADDRESS);

        assertEquals(expected, result.size());
    }

    @Test
    void countReferencesToAddressId() throws SQLException {
        Connection connection = DriverManager.getConnection(DatabaseCredentials.getInstance().getUrl(), DatabaseCredentials.getInstance().getUser(), DatabaseCredentials.getInstance().getPassword());
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO premises values ('p1', 'norr', null, 'id1') ");
        statement.close();
        connection.close();

        long expected = 1;
        long actual = testObject.countReferencesToAddressId(ID);
        assertEquals(expected, actual);
    }

    @Test
    void findByZipCode() {
        int expected = 2;
        List<Address> result = testObject.findByZipCode(ZIP_CODE);

        assertEquals(expected, result.size());
    }

    @Test
    void findByCity() {
        int expected = 2;
        List<Address> result = testObject.findByCity(CITY);

        assertEquals(expected, result.size());
    }

    @Test
    void findByStreetZipCodeAndCity() {
        Optional<Address> result = testObject.findByStreetZipCodeAndCity(STREET_ADDRESS, ZIP_CODE, CITY);
        assertTrue(result.isPresent());
        Address address = result.get();
        assertEquals(ID, address.getId());
    }

    @Test
    void create() {
        Address address = new Address("Hjalmar Petris väg 32", "32244", "Växjö");
        testObject.create(address);

        assertTrue(testObject.findById(address.getId()).isPresent());
    }

    @Test
    void findAll() {
        int expected = 3;
        List<Address> result = testObject.findAll();

        assertEquals(expected, result.size());
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
}