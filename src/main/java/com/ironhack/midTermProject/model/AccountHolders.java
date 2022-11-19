package com.ironhack.midTermProject.model;


import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Entity(name = "account_holders")
@DiscriminatorValue("account_holder")
public class AccountHolders extends Users{

    private LocalDate dateOfBirth;
    @Embedded
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "street", column = @Column(name = "mailing_street")), @AttributeOverride(name = "city", column = @Column(name = "mailing_city"))})
    private Address mailingAddress;

    public AccountHolders(){
        super();
    };
    public AccountHolders(String name, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) throws ParseException {
        super(name);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) throws ParseException {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        if (mailingAddress == null) {
            this.mailingAddress = getPrimaryAddress();
            this.mailingAddress = getPrimaryAddress();
        }
        else {
            this.mailingAddress = mailingAddress;
        }
    }
}
