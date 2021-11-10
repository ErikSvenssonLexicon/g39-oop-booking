package se.lexicon.data;

import se.lexicon.model.UserCredentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCredentialsDAOImpl implements UserCredentialsDAO {

    //SINGLETON, to accomplice Single Source of truth. - One storage of userCredentials for this whole program.

    private static final UserCredentialsDAOImpl INSTANCE;

    static {
        // If you want to seed with some data from File or similar, replace null.
        INSTANCE = new UserCredentialsDAOImpl(null);
    }

    public static UserCredentialsDAOImpl getInstance(){
        return INSTANCE;
    }

    static UserCredentialsDAOImpl getTestInstance(List<UserCredentials> userCredentials){
        return new UserCredentialsDAOImpl(userCredentials);
    }



    protected List<UserCredentials> userCredentialsStorage;

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
        return userCredentialsStorage;

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
    public UserCredentials findByUserName(String userName) {

        for (UserCredentials uc : userCredentialsStorage) {
            if (uc.getUsername().equals(userName)){
                return uc;
            }
        }

        return null;
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
        findById(id)
                .ifPresent(userCredentialsStorage::remove);
        return true;
    }
}
