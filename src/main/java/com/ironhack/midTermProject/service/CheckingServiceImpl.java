package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.*;
import com.ironhack.midTermProject.repository.CheckingRepository;
import com.ironhack.midTermProject.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CheckingServiceImpl implements CheckingService{

    @Autowired
    private CheckingRepository checkingRepository;


    @Override
    public List<Checking> listAllChecking() {
        return checkingRepository.findAll();
    }


    @Override
    public Checking makeTransactionIn(Integer id, AccountsUpdateBalanceDto amountIn) {
        Checking storedAccount = checkingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
        storedAccount.getBalance().increaseAmount(amountIn.getBalance());
        return checkingRepository.save(storedAccount);
    }

    @Override
    public Checking makeTransactionOut(Integer id, AccountsUpdateBalanceDto amountOut) {
        Checking storedAccount = checkingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
        storedAccount.getBalance().decreaseAmount(amountOut.getBalance());
        if (storedAccount.getBalance().getAmount().compareTo(storedAccount.getMinimumBalance().getAmount()) == -1) {
            storedAccount.applyPenaltyFee();
        }
        return checkingRepository.save(storedAccount);
    }

    @Override
    public Checking get(Integer id) {
        Optional<Checking> storedChecking = checkingRepository.findById(id);
        return storedChecking.orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
    }

    @Override
    public void delete(Integer accountId) {
        Optional<Checking> storedChecking = checkingRepository.findById(accountId);
        checkingRepository.delete(storedChecking.orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: \" + id + \" not found.")));
    }

    @Override
    public void deleteAll() {
        checkingRepository.deleteAll();
    }
}
