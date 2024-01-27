package com.example.s29606bank;

public class Client {
    private int id;
    private long balance;

    public Client(int id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }
}
