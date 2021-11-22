package se.lexicon;

import se.lexicon.data.*;
import se.lexicon.data.interfaces.TestTableDAO;
import se.lexicon.io.JSONManager;
import se.lexicon.model.TestTableEntity;

import java.io.File;
import java.io.IOException;

import static se.lexicon.io.URLConstants.*;


/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args ) throws IOException {
        DatabaseCredentials.initialize("credentials/mysql.properties");
        TestTableDAO testTableDAO = new TestTableDAOImpl();
        testTableDAO.findAll().forEach(System.out::println);

    }

    public static void shutdown() {
        JSONManager.getInstance().serializeToJSON(AddressDAOImpl.getInstance().findAll(), new File(ADDRESSES_JSON));
        JSONManager.getInstance().serializeToJSON(BookingDAOImpl.getInstance().findAll(), new File(BOOKINGS_JSON));
        JSONManager.getInstance().serializeToJSON(PremisesDAOImpl.getInstance().findAll(), new File(PREMISES_JSON));
        JSONManager.getInstance().serializeToJSON(ContactInfoDAOImpl.getInstance().findAll(), new File(CONTACT_INFO_JSON));
        JSONManager.getInstance().serializeToJSON(PatientDAOImpl.getInstance().findAll(), new File(PATIENTS_JSON));
        JSONManager.getInstance().serializeToJSON(UserCredentialsDAOImpl.getInstance().findAll(), new File(CREDENTIALS_JSON));
    }
}
