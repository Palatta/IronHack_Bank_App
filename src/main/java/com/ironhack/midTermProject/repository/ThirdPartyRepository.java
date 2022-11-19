package com.ironhack.midTermProject.repository;

import com.ironhack.midTermProject.model.Admins;
import com.ironhack.midTermProject.model.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Integer> {
    ThirdParty findByName(String name);
}
