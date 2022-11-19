package com.ironhack.midTermProject.controller;

import com.ironhack.midTermProject.model.AccountHolders;
import com.ironhack.midTermProject.service.AccountHoldersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class AccountHoldersController {
    @Autowired
    private AccountHoldersService accountHoldersService;


    @PostMapping("/account-holders/new")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolders newAccountHolder(@RequestBody AccountHolders newAccountHolder) {
        return accountHoldersService.addAccountHolder(newAccountHolder);
    }

    @GetMapping("/account-holders")
    public List<AccountHolders> getAllAccountHolders() {
        return accountHoldersService.getAll();
    }

    @PutMapping("/account-holders/{id}")
    public AccountHolders update(@PathVariable Integer id, @RequestBody AccountHolders accountHolder) throws ParseException {
        return accountHoldersService.updateAccountHolder(id, accountHolder);
    }

    @DeleteMapping("/account-holders/{id}")
    public void delete(@PathVariable Integer id) {
        accountHoldersService.delete(id);
    }

    @DeleteMapping("/account-holders/delete-all")
    public void deleteAll() {
        accountHoldersService.deleteAll();
    }

}
