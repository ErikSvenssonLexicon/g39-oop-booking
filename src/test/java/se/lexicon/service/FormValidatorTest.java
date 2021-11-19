package se.lexicon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.TestForm;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FormValidatorTest {

    private FormValidator formValidator;

    @BeforeEach
    void setUp() {
        formValidator = FormValidator.getInstance();
    }

    @Test
    void testFormIsInvalid() {
        TestForm testForm = new TestForm();
        testForm.setId("");
        testForm.setNumber(-1);
        testForm.setTestForm(null);


        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    formValidator.validate(testForm, TestForm.class);

                }
        );

        try{
            formValidator.validate(testForm, TestForm.class);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Test
    void testFormIsValid() {
        TestForm testForm = new TestForm();
        testForm.setId(UUID.randomUUID().toString());
        testForm.setNumber(100);
        testForm.setTestForm(new TestForm());

        assertDoesNotThrow(() -> formValidator.validate(testForm, TestForm.class));

    }
}