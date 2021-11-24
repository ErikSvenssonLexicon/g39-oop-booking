package se.lexicon.data.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.H2Util;
import se.lexicon.data.jdbc.UserCredentialsDAOJdbcImpl;
import se.lexicon.model.UserCredentials;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialsDAOJdbcImplTest {

    public static H2Util h2Util;

    @BeforeAll
    static void beforeAll(){
        h2Util = H2Util.getInstance();
    }

    private UserCredentialsDAOJdbcImpl testObject;

    private UserCredentials testUser;

    @BeforeEach
    void setUp() throws Exception{
        h2Util.dropAndCreateTables();
        testObject = new UserCredentialsDAOJdbcImpl();
        testUser = new UserCredentials(
                "terminator", "hastalavista", "ROLE_USER"
        );
    }

    @Test
    void create() {
        UserCredentials result = testObject.create(testUser);
        assertNotNull(result);
        assertTrue(testObject.findById(result.getId()).isPresent());
    }

    @Test
    void findAll() {
        testObject.create(testUser);
        assertEquals(1, testObject.findAll().size());
    }

    @Test
    void findById() {
        testObject.create(testUser);
        Optional<UserCredentials> result = testObject.findById(testUser.getId());
        assertTrue(result.isPresent());
    }

    @Test
    void delete() {
        testObject.create(testUser);
        assertTrue(testObject.delete(testUser.getId()));
        assertFalse(testObject.findById(testUser.getId()).isPresent());
    }

    @Test
    void update() {
        testObject.create(testUser);
        testUser.setUsername("eastwood");
        testUser.setPassword("do_you_feel_lucky_punk");

        UserCredentials userCredentials = testObject.update(testUser);

        UserCredentials fromDatabase = testObject.findById(testUser.getId()).orElse(null);
        assertNotNull(fromDatabase);
        assertEquals("eastwood", fromDatabase.getUsername());
        assertEquals("do_you_feel_lucky_punk", fromDatabase.getPassword());
        assertEquals("ROLE_USER", fromDatabase.getRole());
    }

    @Test
    void findByUserName() {
        testObject.create(testUser);
        assertTrue(testObject.findByUserName("TERMINATOR").isPresent());
    }

    @Test
    void findByRole() {
        testObject.create(testUser);
        assertEquals(1, testObject.findByRole("ROLE_USER").size());
    }
}