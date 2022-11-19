package com.ironhack.midTermProject.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class NoCreditCard extends Accounts {
    @Column(name = "secret_key")
    private String secretKey;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "monthly_maint_currency")), @AttributeOverride(name = "amount", column = @Column(name = "monthly_maint_amount"))})
    private final Money monthlyMaintenanceFee = new Money(new BigDecimal(12));
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public NoCreditCard(Integer accountId, Money balance, AccountHolders primaryOwner, AccountHolders secondaryOwner, String secretKey, LocalDate creationDate, Status status) {
        super(accountId, balance, primaryOwner, secondaryOwner);
        setSecretKey(secretKey);
        setCreationDate(creationDate);
        setStatus(status);
    }

    public NoCreditCard() {
        super();
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = LocalDate.now();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
