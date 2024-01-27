package com.example.s29606bank.exceptions;

public class ClientIdNotFoundException extends Exception {

    public ClientIdNotFoundException(int clientId) {
        super("Client with id #" + clientId + " not found.");
    }
}
