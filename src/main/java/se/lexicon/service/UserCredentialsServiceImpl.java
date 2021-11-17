package se.lexicon.service;

import se.lexicon.data.interfaces.UserCredentialsDAO;
import se.lexicon.model.UserCredentials;
import se.lexicon.model.dto.UserCredentialsDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserCredentialsServiceImpl implements UserCredentialsService{

    private final UserCredentialsDAO userDAO;
    private final DTOConversionService conversionService;

    public UserCredentialsServiceImpl(UserCredentialsDAO userDAO, DTOConversionService conversionService) {
        this.userDAO = userDAO;
        this.conversionService = conversionService;
    }

    @Override
    public UserCredentialsDTO create(String username, String password, String role) {
        if(username == null || username.isEmpty()){
            throw new IllegalArgumentException("Username is not allowed to be null or empty");
        }
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("Password is not allowed to be null or empty");
        }
        if(role == null || role.isEmpty()){
            throw new IllegalArgumentException("Role is not allowed to be null or empty");
        }
        if(userDAO.findByUserName(username).isPresent()){
            throw new IllegalArgumentException("Username " + username + " is already taken");
        }
        UserCredentials userCredentials = new UserCredentials(username.trim(), password, role.trim());
        userCredentials = userDAO.create(userCredentials);

        return conversionService.toUserCredentialsDTO(userCredentials);
    }

    @Override
    public UserCredentialsDTO findById(String id) {
        return userDAO.findById(id)
                .map(conversionService::toUserCredentialsDTO)
                .orElseThrow(() -> new RuntimeException("Could not find user with id" + id));
    }

    @Override
    public UserCredentialsDTO findByUsername(String username) {
        return userDAO.findByUserName(username.trim())
                .map(conversionService::toUserCredentialsDTO)
                .orElseThrow(() -> new RuntimeException("Could not find user with username " + username));
    }

    @Override
    public List<UserCredentialsDTO> findByRole(String role) {
        return userDAO.findByRole(role.trim()).stream()
                .map(conversionService::toUserCredentialsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserCredentialsDTO> findAll() {
        return userDAO.findAll().stream()
                .map(conversionService::toUserCredentialsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserCredentialsDTO update(String username, UserCredentialsDTO dto) {
        UserCredentials userCredentials = userDAO.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("Update aborted, could not find user with username " + username));

        Optional<UserCredentials> optional = userDAO.findByUserName(dto.getUsername());
        if(optional.isPresent() && !optional.get().getId().equals(userCredentials.getId())){
            throw new RuntimeException("Update aborted, username " + dto.getUsername() + " is already taken");
        }

        userCredentials.setUsername(dto.getUsername());
        userCredentials.setRole(dto.getRole());

        return conversionService.toUserCredentialsDTO(userCredentials);
    }

    @Override
    public boolean delete(String id) {
        return userDAO.delete(id);
    }
}
