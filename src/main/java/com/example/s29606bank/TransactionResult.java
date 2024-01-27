package com.example.s29606bank;

public abstract class TransactionResult {
    private TransactionStatus status;

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
