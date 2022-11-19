package com.ironhack.midTermProject.controller;

import com.ironhack.midTermProject.model.Checking;
import com.ironhack.midTermProject.model.NoCreditCard;
import com.ironhack.midTermProject.service.NoCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoCreditCardController {
    @Autowired
    private NoCreditCardService noCreditCardService;

    @PostMapping("/checking/new")
    @ResponseStatus(HttpStatus.CREATED)
    public NoCreditCard newNoCreditCard(@RequestBody Checking checking) {
        return noCreditCardService.addChecking(checking);
    }
}
