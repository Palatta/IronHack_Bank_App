package com.ironhack.midTermProject.controller;

import com.ironhack.midTermProject.model.AccountsUpdateBalanceDto;
import com.ironhack.midTermProject.model.Checking;
import com.ironhack.midTermProject.model.Savings;
import com.ironhack.midTermProject.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SavingsController {
    @Autowired
    private SavingsService savingsService;

    @PostMapping("/savings/new")
    public Savings newSaving(@RequestBody Savings saving) {
        return savingsService.addSavings(saving);
    }

    @GetMapping("/savings")
    public List<Savings> getAll() { return savingsService.listAll(); }
    @PatchMapping("/savings/{id}/transaction-in")
    public Savings updateBalanceIn(@PathVariable Integer id, @RequestBody AccountsUpdateBalanceDto amountIn) {
        return savingsService.makeTransactionIn(id, amountIn);
    }

    @PatchMapping("/savings/{id}/transaction-out")
    public Savings updateBalanceOut(@PathVariable Integer id, @RequestBody AccountsUpdateBalanceDto amountOut) {
        return savingsService.makeTransactionOut(id, amountOut);
    }
    @GetMapping("/savings/{accountId}")
    public Savings getById(@PathVariable Integer accountId) {
        return savingsService.get(accountId);
    }

    @DeleteMapping("/savings/{accountId}/delete")
    public void deleteById(@PathVariable Integer accountId) { savingsService.delete(accountId);}

    @DeleteMapping("/savings/delete-all")
    public void deleteAll() { savingsService.deleteAll();};
}
