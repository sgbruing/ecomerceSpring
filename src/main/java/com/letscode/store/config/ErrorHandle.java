package com.letscode.store.config;

import com.letscode.store.exceptions.ErrorValidation;
import com.letscode.store.exceptions.InvalidValueFieldException;
import com.letscode.store.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorHandle {

    @Autowired
    private final MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorValidation> HandleErrorValidation(MethodArgumentNotValidException exception){
        List<ErrorValidation> errors = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e ->{
            String mensagem =  messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorValidation error = new ErrorValidation(e.getField(), mensagem);
            errors.add(error);
        });
        return  errors;
    }


    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String NotFoundHandler(NotFoundException exception){
        return exception.getMessage();
    }

    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(InvalidValueFieldException.class)
    public String InvalidValueField(InvalidValueFieldException exception){
        return exception.getMessage();
    }

}
