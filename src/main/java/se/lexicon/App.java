package se.lexicon;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import se.lexicon.data.interfaces.UserCredentialsDAO;
import se.lexicon.data.UserCredentialsDAOImpl;
import se.lexicon.io.JSONManager;
import se.lexicon.model.UserCredentials;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args ) {
        UserCredentialsDAO userCredentialsDAO = UserCredentialsDAOImpl.getInstance();
        userCredentialsDAO.findAll().forEach(System.out::println);





        shutdown();
    }

    public static void shutdown(){
        //Serialize all objects
    }
}
