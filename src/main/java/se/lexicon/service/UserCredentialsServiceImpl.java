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

    public void validate(UserCredentialsForm form){
        if(form == null){
            throw new IllegalArgumentException("Form was null");
        }
        if(form.getUsername() == null || form.getUsername().isEmpty()){
            throw new IllegalArgumentException("Username is not allowed to be null or empty");
        }
        if(form.getPassword() == null || form.getPassword().isEmpty()){
            throw new IllegalArgumentException("Password is not allowed to be null or empty");
        }
        if(userDAO.findByUserName(form.getUsername()).isPresent()){
            throw new IllegalArgumentException("Username " + form.getUsername() + " is already taken");
        }
    }

    @Override
    public UserCredentials create(UserCredentialsForm form, String role) {
        validate(form);
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
    public UserCredentials update(String username, UserCredentialsDTO dto) {
        UserCredentials userCredentials = userDAO.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("Update aborted, could not find user with username " + username));

        Optional<UserCredentials> optional = userDAO.findByUserName(dto.getUsername());
        if(optional.isPresent() && !optional.get().getId().equals(userCredentials.getId())){
            throw new RuntimeException("Update aborted, username " + dto.getUsername() + " is already taken");
        }

        userCredentials.setUsername(dto.getUsername());
        userCredentials.setRole(dto.getRole());

        return userCredentials;
    }

    @Override
    public boolean delete(String id) {
        return userDAO.delete(id);
    }
}
