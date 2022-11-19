package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.AccountsUpdateBalanceDto;
import com.ironhack.midTermProject.model.Checking;
import com.ironhack.midTermProject.model.CreditCard;
import com.ironhack.midTermProject.model.Savings;

import java.util.List;

public interface CreditCardService {
    List<CreditCard> listAllCreditCard();
    CreditCard addCreditCard(CreditCard creditCard);
    CreditCard makeTransactionIn(Integer id, AccountsUpdateBalanceDto amountIn);
    CreditCard makeTransactionOut(Integer id, AccountsUpdateBalanceDto amountOut);
    CreditCard get(Integer id);
    void delete(Integer id);
    void deleteAll();
}
