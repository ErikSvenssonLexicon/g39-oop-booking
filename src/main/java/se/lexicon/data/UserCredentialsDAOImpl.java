package se.lexicon.data;

import se.lexicon.data.interfaces.UserCredentialsDAO;
import se.lexicon.io.JSONManager;
import se.lexicon.model.UserCredentials;

import java.io.File;
import java.util.*;

import static se.lexicon.io.URLConstants.CREDENTIALS_JSON;

public class UserCredentialsDAOImpl implements UserCredentialsDAO {

    //SINGLETON, to accomplice Single Source of truth. - One storage of userCredentials for this whole program.

    private static UserCredentialsDAOImpl INSTANCE;

    public static UserCredentialsDAOImpl getInstance(){
        if(INSTANCE == null) INSTANCE = new UserCredentialsDAOImpl(null);
        return INSTANCE;
    }

    static UserCredentialsDAOImpl getTestInstance(List<UserCredentials> userCredentials){
        if(userCredentials == null) userCredentials = new ArrayList<>();
        return new UserCredentialsDAOImpl(userCredentials);
    }

    private final Set<UserCredentials> userCredentialsStorage;

    private UserCredentialsDAOImpl(Collection<UserCredentials> userCredentials) {
        if (userCredentials == null){
            userCredentialsStorage = new HashSet<>(JSONManager.getInstance().deserializeFromJSON(new File(CREDENTIALS_JSON), UserCredentials.class));
        }else{
            this.userCredentialsStorage = new HashSet<>(userCredentials);
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
