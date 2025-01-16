package com.example.demo.Exception;

public class InvalidTransactionException extends Exception{
    public InvalidTransactionException(String message) {
        super(message);
    }
}
