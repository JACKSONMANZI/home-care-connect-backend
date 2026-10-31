package com.homecare.connect.dashboard;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecare.connect.domain.VisitStatus;
import com.homecare.connect.repository.CareNoteRepository;
import com.homecare.connect.repository.CareRecipientRepository;
import com.homecare.connect.repository.CaregiverRepository;
import com.homecare.connect.repository.HomeVisitRepository;
import com.homecare.connect.repository.ZoneRepository;

@Service 
public class DashboardService {
    
      @Autowired
    private CareRecipientRepository careRecipientRepository;

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private HomeVisitRepository homeVisitRepository;

    @Autowired
    private CareNoteRepository careNoteRepository;

    public Map<String, Long> getDashboardSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("careRecipients", careRecipientRepository.count());
        summary.put("caregivers", caregiverRepository.count());
        summary.put("freeZones", (long) zoneRepository.findFreeZones().size());
        summary.put("visitsToday", homeVisitRepository.countByVisitDate(LocalDate.now()));
        summary.put("requested", homeVisitRepository.countByStatus(VisitStatus.REQUESTED));
        summary.put("careNotes", careNoteRepository.count());
        return summary;
    }
}
