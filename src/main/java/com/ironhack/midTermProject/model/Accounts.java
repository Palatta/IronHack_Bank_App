package com.ironhack.midTermProject.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
@SequenceGenerator(name="myseq", sequenceName="MY_SEQ")
public class Accounts {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="myseq")
    @Column(name = "account_id")
    protected Integer accountId;
    @Embedded
    private Money balance;
    @ManyToOne(cascade = CascadeType.ALL)
    private AccountHolders primaryOwner;
    @ManyToOne
    private AccountHolders secondaryOwner;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "penalty_currency")), @AttributeOverride(name = "amount", column = @Column(name = "penalty_amount"))})
    protected final Money penaltyFee = new Money(new BigDecimal(40));

    public Accounts(Integer accountId, Money balance, AccountHolders primaryOwner, AccountHolders secondaryOwner) {
        this.accountId = accountId;
        setBalance(balance);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
    }

    public Accounts() {

    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolders getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolders primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public Optional<AccountHolders> getSecondaryOwner() {
        return Optional.ofNullable(secondaryOwner);
    }

    public void setSecondaryOwner(AccountHolders secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

//    public Money applyPenaltyFee() {
//        BigDecimal result = balance.getAmount().subtract(penaltyFee.getAmount());
//        return new Money(result);
//    }

}
