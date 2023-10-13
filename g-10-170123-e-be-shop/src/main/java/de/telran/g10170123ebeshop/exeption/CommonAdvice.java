package de.telran.g10170123ebeshop.exeption;

import de.telran.g10170123ebeshop.exeption.exeptions.CustomerIdDoesNotExistException;
import de.telran.g10170123ebeshop.exeption.exeptions.CustomerNotFoundException;
import de.telran.g10170123ebeshop.exeption.exeptions.CustomerValidationException;
import de.telran.g10170123ebeshop.exeption.exeptions.EntityValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonAdvice {

    @ExceptionHandler(CustomerIdDoesNotExistException.class)
    public ResponseEntity<Response> handleException(CustomerIdDoesNotExistException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(EntityValidationException.class)
    public ResponseEntity<Response> handleException(EntityValidationException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(CustomerValidationException.class)
    public ResponseEntity<String> handleCustomerValidationException(CustomerValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return ResponseEntity.notFound().build();
    }



}