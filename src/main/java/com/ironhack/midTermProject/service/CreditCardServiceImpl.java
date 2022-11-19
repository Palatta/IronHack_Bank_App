package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.*;
import com.ironhack.midTermProject.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class CreditCardServiceImpl implements CreditCardService{
    @Autowired
    private CreditCardRepository creditCardRepository;


    @Override
    public CreditCard addCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public List<CreditCard> listAllCreditCard() {
        return creditCardRepository.findAll();
    }


    @Override
    public CreditCard makeTransactionIn(Integer id, AccountsUpdateBalanceDto amountIn) {
        CreditCard storedAccount = creditCardRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
        storedAccount.getBalance().increaseAmount(amountIn.getBalance());
        return creditCardRepository.save(storedAccount);
    }

    @Override
    public CreditCard makeTransactionOut(Integer id, AccountsUpdateBalanceDto amountOut) {
        CreditCard storedAccount = creditCardRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
        if (!(storedAccount.getBalance().getAmount().compareTo(amountOut.getBalance().getAmount()) == -1)) {
            storedAccount.getBalance().decreaseAmount(amountOut.getBalance());
        }
        return creditCardRepository.save(storedAccount);
    }

    @Override
    public CreditCard get(Integer id) {
        Optional<CreditCard> storedChecking = creditCardRepository.findById(id);
        return storedChecking.orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
    }

    @Override
    public void delete(Integer accountId) {
        Optional<CreditCard> storedChecking = creditCardRepository.findById(accountId);
        creditCardRepository.delete(storedChecking.orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: \" + id + \" not found.")));
    }

    @Override
    public void deleteAll() {
        creditCardRepository.deleteAll();
    }
}
