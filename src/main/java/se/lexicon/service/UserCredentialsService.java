package se.lexicon.service;

import se.lexicon.model.UserCredentials;
import se.lexicon.model.dto.forms.UserCredentialsForm;
import se.lexicon.model.dto.views.UserCredentialsDTO;

import java.util.List;

public interface UserCredentialsService {

    UserCredentials create(UserCredentialsForm form, String role);
    UserCredentials findById(String id);
    UserCredentials findByUsername(String username);
    List<UserCredentials> findByRole(String role);
    List<UserCredentials> findAll();
    UserCredentials update(String username, UserCredentialsDTO dto);
    boolean delete(String id);

}
