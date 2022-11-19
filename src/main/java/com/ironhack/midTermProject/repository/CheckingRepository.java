package com.ironhack.midTermProject.repository;

import com.ironhack.midTermProject.model.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {
}
