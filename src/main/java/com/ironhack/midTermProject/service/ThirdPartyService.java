package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.Admins;
import com.ironhack.midTermProject.model.ThirdParty;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;


public interface ThirdPartyService {
    ThirdParty addThirdParty(ThirdParty thirdParty);
    List<ThirdParty> getAll();
    ThirdParty updateThirdParty(String name, ThirdParty thirdParty) throws ParseException;

    void delete(String name);

    void deleteAll();
}
