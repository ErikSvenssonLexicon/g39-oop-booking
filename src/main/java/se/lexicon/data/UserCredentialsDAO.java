package se.lexicon.data;

import se.lexicon.model.UserCredentials;

import java.util.List;

//Responsible for storing and accessing userCredentials objects.
public interface UserCredentialsDAO {

    //CRUD - Create Read Update Delete

    boolean create(UserCredentials userCredentials);
    List<UserCredentials> findAll();
    UserCredentials findById(String id);
    UserCredentials findByUserName(String userName);
    List<UserCredentials> findByRole(String role);

//    UserCredentials update (UserCredentials updated);

    boolean delete(String id);





}
