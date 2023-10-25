package com.mycompany.propertymanagement.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice // as we do this spring will know this is the class which i need to look for a particular handler for whenever that particular exception is thrown
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex){
        System.out.println("BusinessException is thrown");

        for (ErrorModel em : bex.getErrors()){
            logger.debug("BusinessException is thrown- level-debug: {} - {}", em.getCode(), em.getMessage());
            logger.info("BusinessException is thrown- level-info: {} - {}", em.getCode(), em.getMessage());
            logger.warn("BusinessException is thrown - level-warn: {} - {}", em.getCode(), em.getMessage());
            logger.error("BusinessException is thrown- level-error: {} - {}", em.getCode(), em.getMessage());

        }

        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldException(MethodArgumentNotValidException manve){
        System.out.println("invalid inputs");

        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = null;
        List<FieldError> fieldErrorsList = manve.getFieldErrors();


        for(FieldError fe : fieldErrorsList){
            logger.debug("inside field validation: {} - {}", fe.getField(), fe.getDefaultMessage());
            logger.info("inside field validation: {} - {}", fe.getField(), fe.getDefaultMessage());

            errorModel = new ErrorModel();
            errorModel.setCode(fe.getField());
            errorModel.setMessage(fe.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);
    }
}
