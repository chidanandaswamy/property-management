package com.mycompany.propertymanagement.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private List<ErrorModel> errors;

    public BusinessException() {
    }

    public BusinessException(List<ErrorModel> errors) {
        this.errors = errors;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }
}
