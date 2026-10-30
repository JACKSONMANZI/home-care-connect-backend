package com.homecare.connect.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.homecare.connect.domain.CareNote;
import com.homecare.connect.domain.HomeVisit;

public interface CareNoteRepository extends JpaRepository<CareNote,UUID> {
    
    @Query("SELECT n FROM CareNote n WHERE n.homeVisit.careRecipient.nationalId = :nationalId ORDER BY n.notedDate DESC")
    List<CareNote> findByHomeVisitCareRecipientNationalIdOrderByNotedDateDesc(@Param("nationalId") String nationalId);
    boolean existsByHomeVisit(HomeVisit homeVisit);
}
