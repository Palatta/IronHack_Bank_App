package com.ironhack.midTermProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class ThirdParty extends Users{

    @Column(name = "hashed_key")
    private String hashedKey;

    public ThirdParty(){super();}
    public ThirdParty(String name, String hashedKey) {
        super(name);
        setHashedKey(hashedKey);
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
