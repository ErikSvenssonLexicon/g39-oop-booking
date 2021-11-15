package se.lexicon;

import se.lexicon.data.interfaces.UserCredentialsDAO;
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


        UserCredentialsDAO userCredentialsDAO = UserCredentialsDAOImpl.getInstance();

        userCredentialsDAO.create(new UserCredentials("simon.123456","simonelbrink", "Don't Look At My Password", "ROLE_SUPER_ADMIN"));
        userCredentialsDAO.create(new UserCredentials("erik.123456","eriksvensson", "Don't Look At My Password1", "ROLE_SUPER_ADMIN"));


        System.out.println("userCredentialsDAO = " + userCredentialsDAO.findAll());


        //------

        //Consistent with other dao they share the same memory address. on line 28.
        UserCredentialsDAO userCredentialsDAO1 = UserCredentialsDAOImpl.getInstance();


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



//        Object reference example
        Object a = new Object();

        Object b = new Object();

        Object c = a;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c); // Same object address as variable "a"


    }
}
