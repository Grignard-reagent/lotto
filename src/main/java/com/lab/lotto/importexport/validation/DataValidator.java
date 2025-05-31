package com.lab.lotto.importexport.validation;

import java.util.List;
import java.util.Map;

public interface DataValidator<T> {
    ValidationResult validate(T data);
    boolean supports(Class<?> clazz);
} 