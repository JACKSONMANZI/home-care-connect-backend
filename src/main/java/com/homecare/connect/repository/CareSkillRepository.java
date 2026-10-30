package com.homecare.connect.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.homecare.connect.domain.CareSkill;

public interface CareSkillRepository extends JpaRepository<CareSkill,UUID> {
    @Query("SELECT s FROM CareSkill s WHERE s.caregivers IS EMPTY")
    List<CareSkill> findUnusedCareSkills();
}
