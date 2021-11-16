package se.lexicon.data;

import se.lexicon.App;
import se.lexicon.data.interfaces.UserCredentialsDAO;
import se.lexicon.io.JSONManager;
import se.lexicon.model.UserCredentials;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCredentialsDAOImpl implements UserCredentialsDAO {

    //SINGLETON, to accomplice Single Source of truth. - One storage of userCredentials for this whole program.

    private static final UserCredentialsDAOImpl INSTANCE;

    static {
        // If you want to seed with some data from File or similar, replace null.
        INSTANCE = new UserCredentialsDAOImpl(
                JSONManager.getInstance().deserializeFromJSON(new File(App.CREDENTIALS_JSON), UserCredentials.class)
        );
    }

    public static UserCredentialsDAOImpl getInstance(){
        return INSTANCE;
    }

    static UserCredentialsDAOImpl getTestInstance(List<UserCredentials> userCredentials){
        return new UserCredentialsDAOImpl(userCredentials);
    }



    private final List<UserCredentials> userCredentialsStorage;

    private UserCredentialsDAOImpl(List<UserCredentials> userCredentialsList) {
        if (userCredentialsList == null){
            userCredentialsStorage = new ArrayList<>();
        }else{
            this.userCredentialsStorage = userCredentialsList;
        }
    }

    @Override
    public UserCredentials create(UserCredentials userCredentials) {
        userCredentialsStorage.add(userCredentials);
        return userCredentials;
    }

    @Override
    public List<UserCredentials> findAll() {
        return new ArrayList<>(userCredentialsStorage);

    }

    @Override
    public Optional<UserCredentials> findById(String id) {

        for (UserCredentials uc : userCredentialsStorage) {
            if (uc.getId().equals(id)) {
                return Optional.of(uc);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<UserCredentials> findByUserName(String userName) {

        for (UserCredentials uc : userCredentialsStorage) {
            if (uc.getUsername().equals(userName)){
                return Optional.of(uc);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<UserCredentials> findByRole(String role) {

        List<UserCredentials> foundMatches = new ArrayList<>();

        for (UserCredentials uc :
                userCredentialsStorage) {
            if (uc.getRole().equals(role)) {
                foundMatches.add(uc);
            }
        }

        return foundMatches;
    }

    @Override
    public boolean delete(String id) {
        Optional<UserCredentials> optional = findById(id);
        return optional.map(userCredentialsStorage::remove).orElse(false);
    }
}
