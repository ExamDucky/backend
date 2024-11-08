package com.unihack.smart_usb.api.error_handling;


import com.unihack.smart_usb.exception.auth.ProfessorAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(ProfessorAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MainErrorResponse handlerProfessorAlreadyExistsException(ProfessorAlreadyExistsException ex) {
        return MainErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .code("USER_WITHOUT_GROUP")
                .build();
    }
}
