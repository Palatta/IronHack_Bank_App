package com.ironhack.midTermProject.controller;

import com.ironhack.midTermProject.model.AccountsUpdateBalanceDto;
import com.ironhack.midTermProject.model.StudentChecking;
import com.ironhack.midTermProject.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentCheckingController {

    @Autowired
    private StudentCheckingService studentCheckingService;

    @GetMapping("/student-checking")
    public List<StudentChecking> getAllStudentChecking() {return studentCheckingService.listAllStudentChecking();}

    @PatchMapping("/student-checking/{id}/transaction-in")
    public StudentChecking updateBalanceIn(@PathVariable Integer id, @RequestBody AccountsUpdateBalanceDto amountIn) {
        return studentCheckingService.makeTransactionIn(id, amountIn);
    }

    @PatchMapping("/student-checking/{id}/transaction-out")
    public StudentChecking updateBalanceOut(@PathVariable Integer id, @RequestBody AccountsUpdateBalanceDto amountOut) {
        return studentCheckingService.makeTransactionOut(id, amountOut);
    }
    @GetMapping("/student-checking/{accountId}")
    public StudentChecking getById(@PathVariable Integer accountId) {
        return studentCheckingService.get(accountId);
    }

    @DeleteMapping("student-checking/{accountId}/delete")
    public void deleteById(@PathVariable Integer accountId) { studentCheckingService.delete(accountId);}

    @DeleteMapping("/student-checking/delete-all")
    public void deleteAll() { studentCheckingService.deleteAll();};
}
