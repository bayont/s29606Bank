package com.example.s29606bank;

import com.example.s29606bank.exceptions.ClientIdNotFoundException;
import com.example.s29606bank.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientStorage clientStorage;

    @InjectMocks
    private ClientService clientService;


    @Test
    void register() {
        clientService.register(2000);

        verify(clientStorage, times(1)).addClient(any(Client.class));
    }

    @Test
    void shouldSubstractBalance() throws InsufficientBalanceException, ClientIdNotFoundException {
        Client client = new Client(1, 2000);
        when(clientStorage.getClientsList()).thenReturn(List.of(client));


        clientService.subtractBalance(1, 1000);
        long newBalance = clientService.getClientBalance(1);

        assertThat(newBalance).isEqualTo(1000);
    }

    @Test
    void shouldAddBalance() throws ClientIdNotFoundException {

        Client client = new Client(1, 2000);
        when(clientStorage.getClientsList()).thenReturn(List.of(client));

        clientService.addBalance(1, 1000);
        long newBalance = clientService.getClientBalance(1);
        assertThat(newBalance).isEqualTo(3000);
    }

    @Test
    void shouldGetClientBalance() throws ClientIdNotFoundException {
        Client client = new Client(1, 2000);
        when(clientStorage.getClientsList()).thenReturn(List.of(client));

        clientService.getClientBalance(1);
        long balance = clientService.getClientBalance(1);
        assertThat(balance).isEqualTo(2000);
    }

    @Test
    void shouldGetClientByGivenId() throws ClientIdNotFoundException {
        Client client = new Client(1, 2000);
        when(clientStorage.getClientsList()).thenReturn(List.of(client));

        Client clientById = clientService.getClientById(1);
        assertThat(clientById).isEqualTo(client);
    }
}