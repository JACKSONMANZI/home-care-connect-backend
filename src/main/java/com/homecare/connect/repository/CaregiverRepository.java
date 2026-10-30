package com.homecare.connect.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.homecare.connect.domain.Caregiver;

public interface CaregiverRepository extends JpaRepository<Caregiver,UUID>{
    
    @Query("SELECT c FROM Caregiver c JOIN c.careSkills s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Caregiver> findByCareSkillName(@Param("name") String name);
}
