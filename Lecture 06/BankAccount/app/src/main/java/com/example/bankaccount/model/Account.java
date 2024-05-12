package com.example.bankaccount.model;

import androidx.annotation.NonNull;

public class Account {
    private int accountId;
    private String accountClientName;
    private float accountBalance;

    public Account(int accountId, String accountClientName, float accountBalance) {
        this.accountId = accountId;
        this.accountClientName = accountClientName;
        this.accountBalance = accountBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountClientName() {
        return accountClientName;
    }

    public void setAccountClientName(String accountClientName) {
        this.accountClientName = accountClientName;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public boolean withdraw(float value) {
        if (value > accountBalance) return false;

        accountBalance -= value;
        return true;
    }

    public boolean deposit(float value) {
        if (value <= 0.00f) return false;

        accountBalance += value;
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        return "Account" + "\n"
                + "Id=" + accountId + "\n"
                + "Client=" + accountClientName;
    }

}
