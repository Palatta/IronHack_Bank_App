package com.ironhack.midTermProject.repository;

import com.ironhack.midTermProject.model.NoCreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoCreditCardRepository extends JpaRepository<NoCreditCard, Integer> {
}
