package com.ironhack.midTermProject.controller;

import com.ironhack.midTermProject.model.AccountHolders;
import com.ironhack.midTermProject.model.Admins;
import com.ironhack.midTermProject.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class AdminsController {
    @Autowired
    private AdminsService adminsService;

    @PostMapping("/admins/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Admins newAccountHolder(@RequestBody Admins admin) {
        return adminsService.addAdmin(admin);
    }

    @GetMapping("/admins")
    public List<Admins> getAllUsers() {
        return adminsService.getAll();
    }

    @PutMapping("/admins/{name}")
    public Admins updateAdmin(@PathVariable String name, @RequestBody Admins admin) throws ParseException {
        return adminsService.updateAdmin(name, admin);
    }

    @DeleteMapping("/admins/{name}")
    public void delete(@PathVariable String name) {
        adminsService.delete(name);
    }

    @DeleteMapping("/admins/delete-all")
    public void deleteAll() {
        adminsService.deleteAll();
    }

}
