package com.ironhack.midTermProject.service;

import com.ironhack.midTermProject.model.Admins;
import com.ironhack.midTermProject.model.ThirdParty;
import com.ironhack.midTermProject.repository.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService{
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Override
    public ThirdParty addThirdParty(ThirdParty thirdParty) {
        return thirdPartyRepository.save(thirdParty);
    }

    @Override
    public List<ThirdParty> getAll() {
        return thirdPartyRepository.findAll();
    }
    @Override
    public ThirdParty updateThirdParty(String name, ThirdParty thirdParty) throws ParseException {
        ThirdParty storedThirdParty = thirdPartyRepository.findByName(name);
        storedThirdParty.setName(thirdParty.getName());
        return thirdPartyRepository.save(storedThirdParty);
    }

    @Override
    public void delete(String name) {
        ThirdParty thirdParty = thirdPartyRepository.findByName(name);
        thirdPartyRepository.delete(thirdParty);
    }

    @Override
    public void deleteAll() {
        thirdPartyRepository.deleteAll();
    }
}
