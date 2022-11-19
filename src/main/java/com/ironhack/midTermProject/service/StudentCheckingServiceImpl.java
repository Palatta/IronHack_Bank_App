package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.AccountsUpdateBalanceDto;
import com.ironhack.midTermProject.model.Checking;
import com.ironhack.midTermProject.model.StudentChecking;
import com.ironhack.midTermProject.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCheckingServiceImpl implements StudentCheckingService{

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Override
    public List<StudentChecking> listAllStudentChecking() {return studentCheckingRepository.findAll();}
    @Override
    public StudentChecking makeTransactionIn(Integer id, AccountsUpdateBalanceDto amountIn) {
        StudentChecking storedAccount = studentCheckingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
        storedAccount.getBalance().increaseAmount(amountIn.getBalance());
        return studentCheckingRepository.save(storedAccount);
    }

    @Override
    public StudentChecking makeTransactionOut(Integer id, AccountsUpdateBalanceDto amountOut) {
        StudentChecking storedAccount = studentCheckingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
        if (!(storedAccount.getBalance().getAmount().compareTo(amountOut.getBalance().getAmount()) == -1)) {
            storedAccount.getBalance().decreaseAmount(amountOut.getBalance());
        }
        return studentCheckingRepository.save(storedAccount);
    }

    @Override
    public StudentChecking get(Integer id) {
        Optional<StudentChecking> storedChecking = studentCheckingRepository.findById(id);
        return storedChecking.orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: " + id + " not found."));
    }

    @Override
    public void delete(Integer accountId) {
        Optional<StudentChecking> storedChecking = studentCheckingRepository.findById(accountId);
        studentCheckingRepository.delete(storedChecking.orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Checking account with id: \" + id + \" not found.")));
    }

    @Override
    public void deleteAll() {
        studentCheckingRepository.deleteAll();
    }
}
