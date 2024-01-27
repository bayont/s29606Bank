package com.example.s29606bank;

import com.example.s29606bank.exceptions.ClientIdNotFoundException;
import com.example.s29606bank.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {


    @Mock
    private ClientService clientService;

    @InjectMocks
    private TransactionService transactionService;


    @Test
    void shouldMakeTransfer() throws InsufficientBalanceException, ClientIdNotFoundException {
        TransactionResult tr = this.transactionService.makeTransfer(1, 2000);
        verify(clientService).subtractBalance(1, 2000);
        assertThat(tr).isInstanceOf(TransactionResultSuccess.class);
        assertThat(tr.getStatus()).isEqualTo(TransactionStatus.ACCEPTED);

    }

    @Test
    void shouldMakeDeposit() throws ClientIdNotFoundException {
        TransactionResult tr = this.transactionService.deposit(1, 2000);
        verify(clientService).addBalance(1, 2000);
        assertThat(tr).isInstanceOf(TransactionResultSuccess.class);
        assertThat(tr.getStatus()).isEqualTo(TransactionStatus.ACCEPTED);
    }
}