package com.ironhack.midTermProject.controller;

import com.ironhack.midTermProject.model.*;
import com.ironhack.midTermProject.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckingController {
    @Autowired
    private CheckingService checkingService;


    @GetMapping("/checking")
    public List<Checking> getAllChecking() {
        return checkingService.listAllChecking();
    }


    @PatchMapping("/checking/{id}/transaction-in")
    public Checking updateBalanceIn(@PathVariable Integer id, @RequestBody AccountsUpdateBalanceDto amountIn) {
        return checkingService.makeTransactionIn(id, amountIn);
    }

    @PatchMapping("/checking/{id}/transaction-out")
    public Checking updateBalanceOut(@PathVariable Integer id, @RequestBody AccountsUpdateBalanceDto amountOut) {
        return checkingService.makeTransactionOut(id, amountOut);
    }
    @GetMapping("/checking/{accountId}")
    public Checking getById(@PathVariable Integer accountId) {
        return checkingService.get(accountId);
    }

    @DeleteMapping("/checking/{accountId}/delete")
    public void deleteById(@PathVariable Integer accountId) { checkingService.delete(accountId);}

    @DeleteMapping("/checking/delete-all")
    public void deleteAll() { checkingService.deleteAll();};
}
