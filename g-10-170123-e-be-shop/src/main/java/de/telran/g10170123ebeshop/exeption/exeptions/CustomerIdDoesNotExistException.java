package de.telran.g10170123ebeshop.exeption.exeptions;

public class CustomerIdDoesNotExistException extends RuntimeException {

    public CustomerIdDoesNotExistException(String message) {

        super(message);
    }
}