package se.lexicon.service;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class FormValidator {

    private static final FormValidator INSTANCE = new FormValidator();

    private FormValidator() {
    }

    public static FormValidator getInstance(){
        return INSTANCE;
    }

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public <T> void validate(T t, Class<T> clazz){
        if(t != null){
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<T>> validationResult = validator.validate(t);
            if(!validationResult.isEmpty()){
                StringBuilder sb = new StringBuilder("Validation failed for: ");
                sb.append(clazz.getName());

                for(ConstraintViolation<T> violation : validationResult){

                    sb.append("\n ").append(violation.getPropertyPath()).append(": ").append(violation.getMessage());
                }
                throw new IllegalArgumentException(sb.toString());
            }
        }

    }

}
