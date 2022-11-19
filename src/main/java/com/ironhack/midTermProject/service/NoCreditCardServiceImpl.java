package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.Checking;
import com.ironhack.midTermProject.model.NoCreditCard;
import com.ironhack.midTermProject.model.StudentChecking;
import com.ironhack.midTermProject.repository.CheckingRepository;
import com.ironhack.midTermProject.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NoCreditCardServiceImpl implements NoCreditCardService{
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Override
    public NoCreditCard addChecking(Checking checking) {
        if ((LocalDate.now().getYear() - checking.getPrimaryOwner().getDateOfBirth().getYear()) > 24) {
            checking.setCreationDate(LocalDate.now());
            return checkingRepository.save(checking);
        }
        else {
            StudentChecking studentChecking = new StudentChecking();
            studentChecking.setBalance(checking.getBalance());
            studentChecking.setPrimaryOwner(checking.getPrimaryOwner());
            studentChecking.setSecondaryOwner(checking.getSecondaryOwner().orElse(null));
            studentChecking.setStatus(checking.getStatus());
            studentChecking.setCreationDate(checking.getCreationDate());
            studentChecking.setSecretKey(checking.getSecretKey());
            return studentCheckingRepository.save(studentChecking);
        }
    }
}
