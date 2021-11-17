package se.lexicon.service;

import se.lexicon.model.UserCredentials;
import se.lexicon.model.dto.UserCredentialsDTO;

public class DTOConversionServiceImpl implements DTOConversionService{
    @Override
    public UserCredentialsDTO toUserCredentialsDTO(UserCredentials userCredentials) {
        if(userCredentials == null) return null;

        UserCredentialsDTO dto = new UserCredentialsDTO();
        dto.setId(userCredentials.getId());
        dto.setUsername(userCredentials.getUsername());
        dto.setRole(userCredentials.getRole());
        return dto;
    }
}
