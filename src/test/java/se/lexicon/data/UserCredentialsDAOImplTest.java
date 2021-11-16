package se.lexicon.data;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.model.UserCredentials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class UserCredentialsDAOImplTest {

    private UserCredentialsDAOImpl testObject;

    private static final String ID = "erik.123456";
    private static final String USERNAME = "eriksvensson";
    private static final String PASSWORD = "Don't Look At My Password";
    private final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";

    private UserCredentials erik;

    private List<UserCredentials> users = new ArrayList<>(
            Arrays.asList(
                    new UserCredentials(ID, USERNAME, PASSWORD, ROLE_SUPER_ADMIN),
                    new UserCredentials("simon.123456","simonelbrink", "Don't Look At My Password", ROLE_SUPER_ADMIN),
                    new UserCredentials("ulf.123456","ulfbengtsson", "Don't Look At My Password", ROLE_SUPER_ADMIN)
            )
    );

    @BeforeEach
    public void setUp(){
        testObject = UserCredentialsDAOImpl.getTestInstance(users);

        erik = users.get(0);

    }

    @Test
    @DisplayName("Find all return all 3 UserCredential objects")
    public void findAll_successfully(){
        List<UserCredentials> found = testObject.findAll();
        assertEquals(3, found.size());
    }

    @Test
    @DisplayName("Given new UserCredentials object create success")
    void create_success() {
        UserCredentials userCredentials = new UserCredentials("odin","odin",ROLE_SUPER_ADMIN);

        UserCredentials result = testObject.create(userCredentials);
        assertEquals(userCredentials, result);
        assertTrue(testObject.findById(userCredentials.getId()).isPresent());
    }

    @Test
    @DisplayName("Given ID findById return expected object")
    void findById_return_successfully() {
        Optional<UserCredentials> result = testObject.findById(ID);

        assertTrue(result.isPresent());
        assertEquals(erik, result.get());
    }

    @Test
    @DisplayName("Given '123' findById return empty optional")
    void findById_return_empty_optional() {
        assertFalse(testObject.findById("123").isPresent());
    }

    @Test
    @DisplayName("Given String ROLE_SUPER_ADMIN findByRole return 3 objects")
    void findByRole() {
        int expectedSize = 3;

        assertEquals(expectedSize, testObject.findByRole(ROLE_SUPER_ADMIN).size());
    }


    @Test
    @DisplayName("Given 'simonelbrink' findByUsername return optional isPresent = true")
    void findByUsername() {
        assertTrue(testObject.findByUserName("simonelbrink").isPresent());
    }

    @Test
    @DisplayName("Given ID delete successfully remove object")
    void delete_success() {
        assertTrue(testObject.delete(ID));
        assertFalse(testObject.findById(ID).isPresent());
    }
}