package com.example.gradproject.repository;

import com.example.gradproject.model.CreditApplication;
import com.example.gradproject.model.CreditApplicationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditApplicationRepository extends JpaRepository<CreditApplication, CreditApplicationId> {

}
