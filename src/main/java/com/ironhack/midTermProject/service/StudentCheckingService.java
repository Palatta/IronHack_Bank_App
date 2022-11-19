package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.AccountsUpdateBalanceDto;
import com.ironhack.midTermProject.model.Checking;
import com.ironhack.midTermProject.model.StudentChecking;

import java.util.List;

public interface StudentCheckingService {
    List<StudentChecking> listAllStudentChecking();
    StudentChecking makeTransactionIn(Integer id, AccountsUpdateBalanceDto amountIn);
    StudentChecking makeTransactionOut(Integer id, AccountsUpdateBalanceDto amountOut);
    StudentChecking get(Integer id);
    void delete(Integer id);
    void deleteAll();
}
