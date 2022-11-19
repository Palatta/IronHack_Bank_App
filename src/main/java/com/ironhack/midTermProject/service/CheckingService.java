package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.*;

import java.util.List;
import java.util.Optional;

public interface CheckingService {
    List<Checking> listAllChecking();

    Checking makeTransactionIn(Integer id, AccountsUpdateBalanceDto amountIn);
    Checking makeTransactionOut(Integer id, AccountsUpdateBalanceDto amountOut);
    Checking get(Integer id);
    void delete(Integer id);
    void deleteAll();
}
