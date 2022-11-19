package com.ironhack.midTermProject.repository;

import com.ironhack.midTermProject.model.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminsRepository extends JpaRepository<Admins, Integer> {
    Admins findByName(String name);
}
