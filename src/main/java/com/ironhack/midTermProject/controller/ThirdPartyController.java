package com.ironhack.midTermProject.controller;

import com.ironhack.midTermProject.model.Admins;
import com.ironhack.midTermProject.model.ThirdParty;
import com.ironhack.midTermProject.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class ThirdPartyController {
    @Autowired
    public ThirdPartyService thirdPartyService;

    @PostMapping("third-party/new")
    public ThirdParty newThirdParty(@RequestBody ThirdParty thirdParty) {
        return thirdPartyService.addThirdParty(thirdParty);
    }

    @GetMapping("third-party")
    public List<ThirdParty> getAllThirdParty() {
        return thirdPartyService.getAll();
    }
    @PutMapping("/third-party/{name}")
    public ThirdParty updateThirdParty(@PathVariable String name, @RequestBody ThirdParty thirdParty) throws ParseException {
        return thirdPartyService.updateThirdParty(name, thirdParty);
    }

    @DeleteMapping("/third-party/{name}")
    public void delete(@PathVariable String name) {
        thirdPartyService.delete(name);
    }

    @DeleteMapping("/third-party/delete-all")
    public void deleteAll() {
        thirdPartyService.deleteAll();
    }
}
