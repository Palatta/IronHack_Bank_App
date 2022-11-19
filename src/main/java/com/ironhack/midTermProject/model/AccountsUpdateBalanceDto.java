package com.ironhack.midTermProject.model;

import com.sun.istack.NotNull;

public class AccountsUpdateBalanceDto {
    @NotNull
    private Money balance;

    public AccountsUpdateBalanceDto() {}
    public AccountsUpdateBalanceDto(Money balance) {
        this.balance = balance;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
