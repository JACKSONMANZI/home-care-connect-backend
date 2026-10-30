package com.homecare.connect.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.homecare.connect.domain.CareRecipient;

public interface CareRecipientRepository extends JpaRepository<CareRecipient,UUID> {
    
    List<CareRecipient> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT hv.careRecipient FROM HomeVisit hv WHERE hv.caregiver.id = :caregiverId")
    List<CareRecipient> findCareRecipientsOfCaregiver(@Param("caregiverId") UUID caregiverId);
}
