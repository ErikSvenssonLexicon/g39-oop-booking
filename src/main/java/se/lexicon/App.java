package se.lexicon;

import se.lexicon.data.UserCredentialsDAO;
import se.lexicon.data.UserCredentialsDAOImpl;
import se.lexicon.model.UserCredentials;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        UserCredentials[] userCredentials = new UserCredentials[2];
//
//        userCredentials[0] = new UserCredentials();


        List<UserCredentials> userCredentialsList = new ArrayList<>();

        userCredentialsList.add(new UserCredentials("simonelbrink", "Don't Look At My Password", "ROLE_SUPER_ADMIN"));


        UserCredentialsDAO userCredentialsDAO = new UserCredentialsDAOImpl();

        userCredentialsDAO.create(new UserCredentials("simon.123456","simonelbrink", "Don't Look At My Password", "ROLE_SUPER_ADMIN"));
        userCredentialsDAO.create(new UserCredentials("erik.123456","eriksvensson", "Don't Look At My Password1", "ROLE_SUPER_ADMIN"));


        System.out.println("userCredentialsDAO = " + userCredentialsDAO.findAll());


        //------

        //Not Consistent with other dao,
        UserCredentialsDAO userCredentialsDAO1 = new UserCredentialsDAOImpl();


        userCredentialsDAO1.create(new UserCredentials("ulf", "password", "Admin"));

        System.out.println("userCredentialsDAO1 = " + userCredentialsDAO1.findAll());


//        List<UserCredentials> allFound = userCredentialsDAO.findAll();
//
//
//        for (UserCredentials uc : allFound) {
//            System.out.println(uc.getUsername());
//        }

//         UserCredentials user1= userCredentialsDAO.findById("erik.123456");
//        System.out.println(user1.toString());



    }
}
