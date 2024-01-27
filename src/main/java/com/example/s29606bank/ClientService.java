package com.example.s29606bank;

import com.example.s29606bank.exceptions.ClientIdNotFoundException;
import com.example.s29606bank.exceptions.InsufficientBalanceException;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientStorage clientStorage;
    private static int clientIdentificationPointer = 1;

    public ClientService(ClientStorage clientStorage) {
        this.clientStorage = clientStorage;
    }

    public Client register(long balance) {
        Client newClient = new Client(clientIdentificationPointer, balance);
        clientIdentificationPointer++;

        this.clientStorage.addClient(newClient);
        return newClient;
    }

    public void subtractBalance(int clientId, long amountToSubtract) throws InsufficientBalanceException, ClientIdNotFoundException {
        Client client = this.getClientById(clientId);
        if(amountToSubtract > client.getBalance()){
            throw new InsufficientBalanceException();
        }
        client.setBalance(client.getBalance() - amountToSubtract);
    }

    public void addBalance(int clientId, long amountToAdd) throws ClientIdNotFoundException {
        Client client = this.getClientById(clientId);
        client.setBalance(client.getBalance() + amountToAdd);
    }

    public long getClientBalance(int clientId) throws ClientIdNotFoundException {
        return this.getClientById(clientId).getBalance();
    }

    public Client getClientById(int id) throws ClientIdNotFoundException {
        return this.clientStorage
                .getClientsList().stream()
                .filter(client -> client.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ClientIdNotFoundException(id));
    }

}
