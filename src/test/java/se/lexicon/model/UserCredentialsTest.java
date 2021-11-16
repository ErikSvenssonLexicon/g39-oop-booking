package se.lexicon.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserCredentialsTest {

    public static final String USERNAME = "erik.svensson";
    public static final String PASSWORD = "erik1234";
    public static final String ROLE = "ROLE_SUPER_ADMIN";
    private UserCredentials testObject;

    @BeforeEach
    public void setUp() {
        testObject = new UserCredentials(USERNAME, PASSWORD, ROLE);
    }

    @Test
    public void testObject_successfully_instantiated() {
        assertNotNull(testObject.getId());
        assertEquals(USERNAME, testObject.getUsername());
        assertEquals(PASSWORD, testObject.getPassword());
        assertEquals(ROLE, testObject.getRole());
    }

    @Test
    public void constructor_throws_runtime_exception_on_null_id() {
        assertThrows(
                RuntimeException.class,
                () -> new UserCredentials(null, USERNAME, PASSWORD, ROLE)
        );
    }

    @Test
    public void setUsername_null_throws_runtime_exception() {
        assertThrows(
                RuntimeException.class,
                () -> testObject.setUsername(null)
        );
    }

    @Test
    public void setPassword_null_throws_runtime_exception() {
        assertThrows(
                RuntimeException.class,
                () -> testObject.setPassword(null)
        );
    }

    @Test
    public void setRole_null_throws_runtime_exception() {
        assertThrows(
                RuntimeException.class,
                () -> testObject.setRole(null)
        );
    }
}