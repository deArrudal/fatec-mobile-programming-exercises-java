package com.example.bankaccount.model;

import androidx.annotation.NonNull;

public class SpecialAccount extends Account{
    private float specialLimit;

    public SpecialAccount(int accountId, String accountClientName, float accountBalance, float specialLimit) {
        super(accountId, accountClientName, accountBalance);
        this.specialLimit = specialLimit;
    }

    public float getSpecialLimit() {
        return specialLimit;
    }

    public void setSpecialLimit(float specialLimit) {
        this.specialLimit = specialLimit;
    }

    @Override
    public boolean withdraw(float value) {
        if (value > getAccountBalance() + specialLimit) return false;

        setAccountBalance(getAccountBalance() - value);
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        return "SpecialAccount" + "\n"
                + "Id=" + getAccountId() + "\n"
                + "Client=" + getAccountClientName() + "\n"
                + "Limit=" + specialLimit;
    }

}
