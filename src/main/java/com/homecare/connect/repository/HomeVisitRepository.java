package com.homecare.connect.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homecare.connect.domain.HomeVisit;

public interface HomeVisitRepository extends JpaRepository<HomeVisit,UUID> {
    
}
