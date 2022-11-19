package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.AccountHolders;
import com.ironhack.midTermProject.model.Admins;

import java.text.ParseException;
import java.util.List;

public interface AdminsService {
    Admins addAdmin(Admins admin);
    List<Admins> getAll();

    Admins updateAdmin(String name, Admins admin) throws ParseException;

    void delete(String name);

    void deleteAll();
}
