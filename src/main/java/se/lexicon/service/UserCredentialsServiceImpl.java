package se.lexicon.service;

import se.lexicon.data.interfaces.UserCredentialsDAO;
import se.lexicon.exception.AppResourceNotFoundException;
import se.lexicon.model.UserCredentials;
import se.lexicon.model.dto.forms.UserCredentialsForm;
import se.lexicon.model.dto.views.UserCredentialsDTO;

import java.util.List;
import java.util.Optional;

public class UserCredentialsServiceImpl implements UserCredentialsService{

    private final UserCredentialsDAO userDAO;

    public UserCredentialsServiceImpl(UserCredentialsDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserCredentials create(UserCredentialsForm form, String role) {
        if(form == null) throw new IllegalArgumentException("Form was null");
        if(role == null) throw new IllegalArgumentException("Role was null");

        FormValidator.getInstance().validate(form, UserCredentialsForm.class);

        if(userDAO.findByUserName(form.getUsername()).isPresent()){
            throw new IllegalArgumentException("Username " + form.getUsername() + " is already taken");
        }

        UserCredentials userCredentials = new UserCredentials(form.getUsername(), form.getPassword(), role);
        userCredentials = userDAO.create(userCredentials);

        return userCredentials;
    }

    @Override
    public UserCredentials findById(String id) {
        return userDAO.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find user with id" + id));
    }

    @Override
    public UserCredentials findByUsername(String username) {
        return userDAO.findByUserName(username.trim())
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find user with username " + username));
    }

    @Override
    public List<UserCredentials> findByRole(String role) {
        return userDAO.findByRole(role);
    }

    @Override
    public List<UserCredentials> findAll() {
        return userDAO.findAll();
    }

    @Override
    public UserCredentials update(String username, UserCredentialsForm form) {
        if(username == null) throw new IllegalArgumentException("Username was null");

        UserCredentials userCredentials = findByUsername(username);

        Optional<UserCredentials> optional = userDAO.findByUserName(form.getUsername());
        if(optional.isPresent() && !optional.get().getId().equals(userCredentials.getId())){
            throw new RuntimeException("Update aborted, username " + form.getUsername() + " is already taken");        }

        userCredentials.setUsername(form.getUsername());

        return userCredentials;
    }

    @Override
    public boolean delete(String id) {
        return userDAO.delete(id);
    }
}
