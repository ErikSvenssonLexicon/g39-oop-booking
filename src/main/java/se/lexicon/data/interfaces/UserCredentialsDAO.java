package se.lexicon.data.interfaces;

import se.lexicon.model.UserCredentials;

import java.util.List;
import java.util.Optional;

//Responsible for storing and accessing userCredentials objects.
public interface UserCredentialsDAO extends GenericCRUD<UserCredentials, String> {

    //CRUD - Create Read Update Delete
    Optional<UserCredentials> findByUserName(String userName);
    List<UserCredentials> findByRole(String role);
//    UserCredentials update (UserCredentials updated);







}
