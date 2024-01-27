package com.example.s29606bank;

import com.example.s29606bank.exceptions.ClientIdNotFoundException;
import com.example.s29606bank.exceptions.InsufficientBalanceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    private final ClientService clientService;

    public TransactionService(ClientService clientService) {
        this.clientService = clientService;
    }

    public TransactionResult makeTransfer(int senderClientId, long transferAmount) {
        try {
            this.clientService.subtractBalance(senderClientId, transferAmount);
            long clientNewBalance = this.clientService.getClientBalance(senderClientId);
            return new TransactionResultSuccess(clientNewBalance);
        }
        catch (ClientIdNotFoundException exception) {
            return new TransactionResultFailure(TransactionFailureReason.CLIENT_ID_NOT_FOUND);
        }
        catch (InsufficientBalanceException exception) {
            return new TransactionResultFailure(TransactionFailureReason.INSUFFICIENT_BALANCE);
        }
    }

    public TransactionResult deposit(int recipientClientId, long depositAmount) {
        TransactionResult transactionResult;
        try {
            this.clientService.addBalance(recipientClientId, depositAmount);
            long clientNewBalance = this.clientService.getClientBalance(recipientClientId);
            return new TransactionResultSuccess(clientNewBalance);
        }
        catch (ClientIdNotFoundException exception) {
            return new TransactionResultFailure(TransactionFailureReason.CLIENT_ID_NOT_FOUND);
        }
    }

}
