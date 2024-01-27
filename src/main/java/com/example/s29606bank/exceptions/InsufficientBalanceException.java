package com.example.s29606bank.exceptions;

public class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException() {
        super("Insufficient balance to complete the action.");
    }
}
