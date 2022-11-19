package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.AccountHolders;

import java.text.ParseException;
import java.util.List;

public interface AccountHoldersService {
    AccountHolders addAccountHolder(AccountHolders accountHolder);
    List<AccountHolders> getAll();
    AccountHolders updateAccountHolder(Integer id, AccountHolders accountHolder) throws ParseException;

    void delete(Integer id);
    void deleteAll();
}
