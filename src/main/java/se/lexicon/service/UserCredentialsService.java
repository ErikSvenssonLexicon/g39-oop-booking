package se.lexicon.service;

import se.lexicon.model.dto.UserCredentialsDTO;

import java.util.List;

public interface UserCredentialsService {

    UserCredentialsDTO create(String username, String password, String role);
    UserCredentialsDTO findById(String id);
    UserCredentialsDTO findByUsername(String username);
    List<UserCredentialsDTO> findByRole(String role);
    List<UserCredentialsDTO> findAll();
    UserCredentialsDTO update(String username, UserCredentialsDTO dto);
    boolean delete(String id);

}
