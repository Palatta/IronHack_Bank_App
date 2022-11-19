package com.ironhack.midTermProject.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Admins extends Users {
    public Admins(String name) {
        super(name);
    }

    public Admins() {}
}

