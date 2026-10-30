package com.homecare.connect.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecare.connect.domain.CareNote;
import com.homecare.connect.domain.HomeVisit;
import com.homecare.connect.domain.VisitStatus;
import com.homecare.connect.repository.CareNoteRepository;
import com.homecare.connect.repository.HomeVisitRepository;

@Service 
public class CareNoteService {
    @Autowired 
    private CareNoteRepository careNoteRepository;

     @Autowired
    private HomeVisitRepository homeVisitRepository;

    public String createCareNote(UUID homeVisitId, CareNote careNote) {
        Optional<HomeVisit> visitOptional = homeVisitRepository.findById(homeVisitId);
        if (visitOptional.isEmpty()) {
            return "The home visit with that id does not exist";
        }
        HomeVisit visit = visitOptional.get();
        if (visit.getStatus() != VisitStatus.VISITED) {
            return "Care notes are only for VISITED home visits";
        }
        careNote.setNotedDate(LocalDate.now());
        careNote.setHomeVisit(visit);
        careNoteRepository.save(careNote);
        return "Care note saved successfully";
    }

    public List<CareNote> getCareNotesByRecipientNationalId(String nationalId) {
        return careNoteRepository.findByHomeVisitCareRecipientNationalIdOrderByNotedDateDesc(nationalId);
    }

    public String deleteCareNote(UUID id) {
        Optional<CareNote> existing = careNoteRepository.findById(id);
        if (existing.isEmpty()) {
            return "The care note with that id does not exist";
        }
        careNoteRepository.deleteById(id);
        return "Care note deleted successfully";
    }
    
}
