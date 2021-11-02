package se.lexicon.data;

import se.lexicon.model.UserCredentials;

import java.util.ArrayList;
import java.util.List;

public class UserCredentialsDAOImpl implements UserCredentialsDAO {
    protected List<UserCredentials> userCredentialsStorage = new ArrayList<>();

    @Override
    public boolean create(UserCredentials userCredentials) {
        boolean isSaved = userCredentialsStorage.add(userCredentials);
        return isSaved;
    }

    @Override
    public List<UserCredentials> findAll() {
        return userCredentialsStorage;

    }

    @Override
    public UserCredentials findById(String id) {

        for (UserCredentials uc : userCredentialsStorage) {
            if (uc.getId().equals(id)) {
                return uc;
            }
        }

        return null;
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

        UserCredentials foundById = findById(id);

        boolean wasDeleted = userCredentialsStorage.remove(foundById);

        return wasDeleted;
    }
}
