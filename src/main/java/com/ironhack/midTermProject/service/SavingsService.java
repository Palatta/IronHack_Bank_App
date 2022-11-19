package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.AccountsUpdateBalanceDto;
import com.ironhack.midTermProject.model.Checking;
import com.ironhack.midTermProject.model.Savings;

import java.util.List;

public interface SavingsService {
    Savings addSavings(Savings saving);
    List<Savings> listAll();
    Savings makeTransactionIn(Integer id, AccountsUpdateBalanceDto amountIn);
    Savings makeTransactionOut(Integer id, AccountsUpdateBalanceDto amountOut);
    Savings get(Integer id);
    void delete(Integer id);
    void deleteAll();
}
