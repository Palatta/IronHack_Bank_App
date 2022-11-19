package com.ironhack.midTermProject.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Checking extends NoCreditCard {
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "min_currency")), @AttributeOverride(name = "amount", column = @Column(name = "min_amount"))})
    private final Money minimumBalance = new Money(new BigDecimal("250"));


    public Checking(){super();}

    public Checking(Integer accountId, Money balance, AccountHolders primaryOwner, AccountHolders secondaryOwner, String secretKey, LocalDate creationDate, Status status) {
        super(accountId, balance, primaryOwner, secondaryOwner, secretKey, creationDate, status);
    }


    public Money getMinimumBalance() {
        return minimumBalance;
    }


    public void applyPenaltyFee() {
        if (getBalance().getAmount().compareTo(minimumBalance.getAmount()) == -1) {
            getBalance().decreaseAmount(penaltyFee);
        }
    }

}
