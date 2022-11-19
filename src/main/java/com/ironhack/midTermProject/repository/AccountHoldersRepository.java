package com.ironhack.midTermProject.repository;

import com.ironhack.midTermProject.model.AccountHolders;
import com.ironhack.midTermProject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHoldersRepository extends JpaRepository<AccountHolders, Integer> {
}
