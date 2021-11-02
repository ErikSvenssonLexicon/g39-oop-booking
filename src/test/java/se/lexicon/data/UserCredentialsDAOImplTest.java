package se.lexicon.data;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.model.UserCredentials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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

    @Before
    public void setUp(){
        testObject = UserCredentialsDAOImpl.getTestInstance(users);

        erik = users.get(0);

    }

    @Test
    public void create_successfully(){

        UserCredentials toSave = new UserCredentials("testaccount", "124356", ROLE_SUPER_ADMIN);

        boolean wasSaved = testObject.create(toSave);

        assertTrue(wasSaved);
        assertTrue(testObject.userCredentialsStorage.contains(toSave));

    }

    @Test
    public void findAll_successfully(){

        List<UserCredentials> found = testObject.findAll();

        assertEquals(3, found.size());
    }





}