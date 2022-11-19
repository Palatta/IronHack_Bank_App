package com.ironhack.midTermProject.repository;

import com.ironhack.midTermProject.model.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Integer> {
}
