package se.lexicon.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCredentialsTest {

    public static final String USERNAME = "erik.svensson";
    public static final String PASSWORD = "erik1234";
    public static final String ROLE = "ROLE_SUPER_ADMIN";
    private UserCredentials testObject;

    @Before
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

    @Test(expected = RuntimeException.class)
    public void constructor_throws_runtime_exception_on_null_id() {
        new UserCredentials(null, USERNAME, PASSWORD, ROLE);
    }

    @Test(expected = RuntimeException.class)
    public void setUsername_null_throws_runtime_exception() {
        testObject.setUsername(null);
    }

    @Test(expected = RuntimeException.class)
    public void setPassword_null_throws_runtime_exception() {
        testObject.setPassword(null);
    }

    @Test(expected = RuntimeException.class)
    public void setRole_null_throws_runtime_exception() {
        testObject.setRole(null);
    }
}