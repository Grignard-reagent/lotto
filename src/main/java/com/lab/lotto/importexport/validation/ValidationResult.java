package com.lab.lotto.importexport.validation;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResult {
    private boolean valid;
    private List<String> errors;

    public ValidationResult() {
        this.valid = true;
        this.errors = new ArrayList<>();
    }

    public void addError(String error) {
        this.valid = false;
        this.errors.add(error);
    }

    public static ValidationResult success() {
        return new ValidationResult();
    }

    public static ValidationResult fail(String error) {
        ValidationResult result = new ValidationResult();
        result.addError(error);
        return result;
    }
} 