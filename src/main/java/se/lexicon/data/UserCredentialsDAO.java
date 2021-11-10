package se.lexicon.data;

import se.lexicon.model.UserCredentials;

import java.util.List;

//Responsible for storing and accessing userCredentials objects.
public interface UserCredentialsDAO extends GenericCRUD<UserCredentials, String> {

    //CRUD - Create Read Update Delete


    UserCredentials findByUserName(String userName);
    List<UserCredentials> findByRole(String role);

//    UserCredentials update (UserCredentials updated);







}
