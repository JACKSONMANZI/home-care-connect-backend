package com.homecare.connect.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homecare.connect.domain.HomeVisit;
import com.homecare.connect.domain.VisitStatus;

import java.util.List;


public interface HomeVisitRepository extends JpaRepository<HomeVisit,UUID> {
    
    List<HomeVisit> findByStatusOrderByVisitDateAsc(VisitStatus status);
}
