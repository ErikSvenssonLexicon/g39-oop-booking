package se.lexicon.service;

import se.lexicon.model.UserCredentials;
import se.lexicon.model.dto.UserCredentialsDTO;

public interface DTOConversionService {
    UserCredentialsDTO toUserCredentialsDTO(UserCredentials userCredentials);
}
