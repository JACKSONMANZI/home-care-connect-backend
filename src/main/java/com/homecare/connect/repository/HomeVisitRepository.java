package com.homecare.connect.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.homecare.connect.domain.HomeVisit;
import com.homecare.connect.domain.VisitStatus;

import java.util.List;
import java.time.LocalDate;



public interface HomeVisitRepository extends JpaRepository<HomeVisit,UUID> {
    
    List<HomeVisit> findByStatusOrderByVisitDateAsc(VisitStatus status);
    List<HomeVisit> findByVisitDateBetweenOrderByVisitDateAsc(LocalDate start,LocalDate end);
    @Query("SELECT hv.status, COUNT(hv) FROM HomeVisit hv GROUP BY hv.status")
    List<Object[]> countVisitsByStatus();
}
