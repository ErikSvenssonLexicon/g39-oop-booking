package se.lexicon;

import se.lexicon.data.*;
import se.lexicon.io.JSONManager;

import java.io.File;

import static se.lexicon.io.URLConstants.*;


/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args ) {
        shutdown();
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
