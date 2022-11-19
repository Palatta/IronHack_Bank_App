package com.ironhack.midTermProject.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Entity
public class Savings extends NoCreditCard{

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "minimum_currency")), @AttributeOverride(name = "amount", column = @Column(name = "minimum_amount"))})
    private Money minimumBalance;
    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    public Savings(){}
    public Savings(Integer accountId, Money balance, AccountHolders primaryOwner, AccountHolders secondaryOwner, String secretKey, LocalDate creationDate, Status status, Money minimumBalance, BigDecimal interestRate) {
        super(accountId, balance, primaryOwner, secondaryOwner, secretKey, creationDate, status);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        if (minimumBalance != null && (minimumBalance.getAmount().compareTo(new BigDecimal("100")) == 1 || minimumBalance.getAmount().compareTo(new BigDecimal("100")) == 0)) {
            this.minimumBalance = minimumBalance;
        }
        else {
            this.minimumBalance = new Money(new BigDecimal("1000"));
        }
    }
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate != null && (interestRate.compareTo(new BigDecimal("0.5")) == -1 || interestRate.compareTo(new BigDecimal("0")) == 1 && interestRate.compareTo(new BigDecimal("0.5")) == 0)) {
            this.interestRate = interestRate;
        }
        else {
            this.interestRate = new BigDecimal("0.0025");
        }
    }

    public void applyPenaltyFee() {
        if (getBalance().getAmount().compareTo(minimumBalance.getAmount()) == -1) {
            getBalance().decreaseAmount(penaltyFee);
        }
    }
}
