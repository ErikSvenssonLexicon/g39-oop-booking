package se.lexicon.data.interfaces;

import se.lexicon.model.UserCredentials;

import java.util.List;
import java.util.Optional;

public interface GenericCRUD <T, ID>{
    T create(T t);
    List<T> findAll();
    Optional<T> findById(ID id);
    boolean delete(ID id);
}
