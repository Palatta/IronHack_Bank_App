package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.*;
import com.ironhack.midTermProject.repository.AdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class AdminsServiceImpl implements AdminsService{
    @Autowired
    private AdminsRepository adminsRepository;

    @Override
    public Admins addAdmin(Admins admin) {
        return adminsRepository.save(admin);
    }

    @Override
    public List<Admins> getAll() {
        return adminsRepository.findAll();
    }

    @Override
    public Admins updateAdmin(String name, Admins admin) throws ParseException {
        Admins storedAdmin = adminsRepository.findByName(name);
        storedAdmin.setName(admin.getName());
        return adminsRepository.save(storedAdmin);
    }

    @Override
    public void delete(String name) {
        Admins admin = adminsRepository.findByName(name);
        adminsRepository.delete(admin);
    }

    @Override
    public void deleteAll() {
        adminsRepository.deleteAll();
    }
}
