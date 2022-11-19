package com.ironhack.midTermProject.controller;

import com.ironhack.midTermProject.model.AccountsUpdateBalanceDto;
import com.ironhack.midTermProject.model.CreditCard;
import com.ironhack.midTermProject.model.Savings;
import com.ironhack.midTermProject.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/credit-card")
    public List<CreditCard> getAllCreditCard() {return creditCardService.listAllCreditCard();}

    @PostMapping("/credit-card/new")
    public CreditCard newCreditCard(@RequestBody CreditCard creditCard) {
        return creditCardService.addCreditCard(creditCard);
    }

    @PatchMapping("/credit-card/{id}/transaction-in")
    public CreditCard updateBalanceIn(@PathVariable Integer id, @RequestBody AccountsUpdateBalanceDto amountIn) {
        return creditCardService.makeTransactionIn(id, amountIn);
    }

    @PatchMapping("/credit-card/{id}/transaction-out")
    public CreditCard updateBalanceOut(@PathVariable Integer id, @RequestBody AccountsUpdateBalanceDto amountOut) {
        return creditCardService.makeTransactionOut(id, amountOut);
    }
    @GetMapping("/credit-card/{accountId}")
    public CreditCard getById(@PathVariable Integer accountId) {
        return creditCardService.get(accountId);
    }

    @DeleteMapping("/credit-card/{accountId}/delete")
    public void deleteById(@PathVariable Integer accountId) { creditCardService.delete(accountId);}

    @DeleteMapping("/credit-card/delete-all")
    public void deleteAll() { creditCardService.deleteAll();};
}
