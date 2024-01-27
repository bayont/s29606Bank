package com.example.s29606bank;

import com.example.s29606bank.exceptions.ClientIdNotFoundException;
import com.example.s29606bank.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionITest {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ClientService clientService;

    @MockBean // mockujemy tylko storage, bo nie chcemy, żeby testy wpływały na stan bazy danych
    private ClientStorage clientStorage;

    @Test
    void shouldMakeProperTransfer() throws InsufficientBalanceException, ClientIdNotFoundException {
        Client client = new Client(1, 10000);
        when(clientStorage.getClientsList()).thenReturn(List.of(client));

        this.transactionService.makeTransfer(1, 2000);

        assertEquals(8000, this.clientService.getClientBalance(1));
    }

    @Test
    void shouldMakeProperDeposit() throws ClientIdNotFoundException {
        Client client = new Client(1, 10000);
        when(clientStorage.getClientsList()).thenReturn(List.of(client));

        this.transactionService.deposit(1, 2000);

        assertEquals(12000, this.clientService.getClientBalance(1));
    }

    @Test
    void shouldGiveTransactionResultInsufficientBalanceException()  {
        Client client = new Client(1, 10000);
        when(clientStorage.getClientsList()).thenReturn(List.of(client));

        TransactionResult tr = this.transactionService.makeTransfer(1, 20000);
        assertThat(tr).isInstanceOf(TransactionResultFailure.class);
        assertThat(tr.getStatus()).isEqualTo(TransactionStatus.DECLINED);
        assertThat(((TransactionResultFailure) tr).getTransactionFailureReason()).isEqualTo(TransactionFailureReason.INSUFFICIENT_BALANCE);
    };

    @Test
    void shouldGiveTransactionResultClientIdNotFoundException() {
        Client client = new Client(1, 10000);
        when(clientStorage.getClientsList()).thenReturn(List.of(client));

        TransactionResult tr = this.transactionService.makeTransfer(2, 2000);
        assertThat(tr).isInstanceOf(TransactionResultFailure.class);
        assertThat(tr.getStatus()).isEqualTo(TransactionStatus.DECLINED);
        assertThat(((TransactionResultFailure) tr).getTransactionFailureReason()).isEqualTo(TransactionFailureReason.CLIENT_ID_NOT_FOUND);
    };

}
