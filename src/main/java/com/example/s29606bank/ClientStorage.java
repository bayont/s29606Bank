package com.example.s29606bank;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientStorage {
    private List<Client> clientsList = new ArrayList<>();

    public void addClient(Client client) {
        this.clientsList.add(client);
    }

    public void removeClient(Client client) {
        this.clientsList.remove(client);
    }

    public List<Client> getClientsList() {
        return clientsList;
    }
}
