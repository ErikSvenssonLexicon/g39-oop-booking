package se.lexicon;





import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public class TestForm implements Serializable {
    @NotBlank(message = "Id field is mandatory")
    private String id;
    @Positive(message = "Need to be a positive integer")
    private int number;
    @NotNull(message = "This is a mandatory field")
    private TestForm testForm;

    public TestForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TestForm getTestForm() {
        return testForm;
    }

    public void setTestForm(TestForm testForm) {
        this.testForm = testForm;
    }
}
