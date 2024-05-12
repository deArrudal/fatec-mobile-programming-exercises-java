package com.example.bankaccount.model;

import androidx.annotation.NonNull;

public class SavingAccount extends Account {
    private int savingYieldDay;

    public SavingAccount(int accountId, String accountClientName, float accountBalance, int savingYieldDay) {
        super(accountId, accountClientName, accountBalance);
        this.savingYieldDay = savingYieldDay;
    }

    public int getSavingsYieldDay() {
        return savingYieldDay;
    }

    public void setSavingsYieldDay(int savingsYieldDay) {
        this.savingYieldDay = savingsYieldDay;
    }

    public void computeNewBalance(float yieldRate) {
            setAccountBalance(getAccountBalance() * (1 + yieldRate));
    }

    @NonNull
    @Override
    public String toString() {
        return "SavingAccount" + "\n"
                + "Id=" + getAccountId() + "\n"
                + "Client=" + getAccountClientName() + "\n"
                + "YieldDay=" + savingYieldDay;
    }

}
