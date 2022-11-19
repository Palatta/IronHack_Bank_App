package com.ironhack.midTermProject.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Entity
public class StudentChecking extends NoCreditCard{
    public StudentChecking() {
        super();
    }

    public StudentChecking(Integer accountId, Money balance, AccountHolders primaryOwner, AccountHolders secondaryOwner, String secretKey, LocalDate creationDate, Status status) {
        super(accountId, balance, primaryOwner, secondaryOwner, secretKey, creationDate, status);
    }
}
