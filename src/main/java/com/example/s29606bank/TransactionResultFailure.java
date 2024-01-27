package com.example.s29606bank;

public class TransactionResultFailure extends TransactionResult {
    private final TransactionFailureReason transactionFailureReason;
    public TransactionResultFailure(TransactionFailureReason transactionFailureReason) {
        super();
        this.setStatus(TransactionStatus.DECLINED);
        this.transactionFailureReason = transactionFailureReason;
    }

    public TransactionFailureReason getTransactionFailureReason() {
        return transactionFailureReason;
    }
}
