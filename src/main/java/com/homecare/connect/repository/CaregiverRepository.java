package com.homecare.connect.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homecare.connect.domain.Caregiver;

public interface CaregiverRepository extends JpaRepository<Caregiver,UUID>{
    
}
