package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.AccountsUpdateBalanceDto;
import com.ironhack.midTermProject.model.Checking;
import com.ironhack.midTermProject.model.Savings;
import com.ironhack.midTermProject.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SavingsServiceImpl implements SavingsService{
    @Autowired
    private SavingsRepository savingsRepository;

    @Override
    public Savings addSavings(Savings saving) {
        return savingsRepository.save(saving);
    }

    @Override
    public List<Savings> listAll() {
        return savingsRepository.findAll();
    }
    @Override
    public Savings makeTransactionIn(Integer id, AccountsUpdateBalanceDto amountIn) {
        Savings storedAccount = savingsRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
        storedAccount.getBalance().increaseAmount(amountIn.getBalance());
        return savingsRepository.save(storedAccount);
    }

    @Override
    public Savings makeTransactionOut(Integer id, AccountsUpdateBalanceDto amountOut) {
        Savings storedAccount = savingsRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
        storedAccount.getBalance().decreaseAmount(amountOut.getBalance());
        if (storedAccount.getBalance().getAmount().compareTo(storedAccount.getMinimumBalance().getAmount()) == -1) {
            storedAccount.applyPenaltyFee();
        }
        return savingsRepository.save(storedAccount);
    }

    @Override
    public Savings get(Integer id) {
        Optional<Savings> storedChecking = savingsRepository.findById(id);
        return storedChecking.orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
    }

    @Override
    public void delete(Integer accountId) {
        Optional<Savings> storedChecking = savingsRepository.findById(accountId);
        savingsRepository.delete(storedChecking.orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: \" + id + \" not found.")));
    }

    @Override
    public void deleteAll() {
        savingsRepository.deleteAll();
    }
}
