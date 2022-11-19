package com.ironhack.midTermProject.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
public class CreditCard extends Accounts {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "credit_lim_currency")), @AttributeOverride(name = "amount", column = @Column(name = "credit_lim_amount"))})
    private Money creditLimit;
    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    public CreditCard() {super();}

    public CreditCard(Integer accountId, Money balance, AccountHolders primaryOwner, AccountHolders secondaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(accountId, balance, primaryOwner, secondaryOwner);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        if (creditLimit != null && (creditLimit.getAmount().compareTo(new BigDecimal("100")) == 1 || creditLimit.getAmount().compareTo(new BigDecimal("100")) == 0 || creditLimit.getAmount().compareTo(new BigDecimal("100000")) == -1 || creditLimit.getAmount().compareTo(new BigDecimal("100000")) == 0)) {
            this.creditLimit = creditLimit;
        }
        else {
            this.creditLimit = new Money(new BigDecimal("100"));
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate != null && (interestRate.compareTo(new BigDecimal("0.1")) == 0 || interestRate.compareTo(new BigDecimal("0.1")) == 1) || interestRate.compareTo(new BigDecimal("0.2")) == 0 || interestRate.compareTo(new BigDecimal("0.2")) == -1) {
            this.interestRate = interestRate;
        }
        else {
            this.interestRate = new BigDecimal("0.2");
        }
    }
}
