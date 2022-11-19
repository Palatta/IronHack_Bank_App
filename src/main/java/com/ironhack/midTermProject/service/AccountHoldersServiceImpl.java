package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.AccountHolders;
import com.ironhack.midTermProject.repository.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class AccountHoldersServiceImpl implements AccountHoldersService{

    @Autowired
    private AccountHoldersRepository accountHoldersRepository;


    @Override
    public AccountHolders addAccountHolder(AccountHolders accountHolder) {
        return accountHoldersRepository.save(accountHolder);
    }

    @Override
    public List<AccountHolders> getAll() {
        return accountHoldersRepository.findAll();
    }

    @Override
    public AccountHolders updateAccountHolder(Integer id, AccountHolders accountHolder) throws ParseException {
        AccountHolders storedAccountHolder = accountHoldersRepository.findById(id).orElseThrow();
        storedAccountHolder.setName(accountHolder.getName());
        storedAccountHolder.setDateOfBirth(accountHolder.getDateOfBirth());
        storedAccountHolder.setPrimaryAddress(accountHolder.getPrimaryAddress());
        storedAccountHolder.setMailingAddress(accountHolder.getMailingAddress());
        return accountHoldersRepository.save(storedAccountHolder);
    }

    @Override
    public void delete(Integer id) {
        AccountHolders accountHolder = accountHoldersRepository.findById(id).orElseThrow();
        accountHoldersRepository.delete(accountHolder);
    }

    @Override
    public void deleteAll() {
        accountHoldersRepository.deleteAll();
    }
}
