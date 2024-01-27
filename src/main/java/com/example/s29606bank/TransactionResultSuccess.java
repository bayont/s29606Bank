package com.example.s29606bank;

public class TransactionResultSuccess extends TransactionResult {
    private final long newBalance; // nie zapłacili mi za reklamę :(
    public TransactionResultSuccess(long newBalance) {
        super();
        this.setStatus(TransactionStatus.ACCEPTED);
        this.newBalance = newBalance;
    }

    public long getNewBalance() {
        return newBalance;
    }
}
