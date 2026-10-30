package com.homecare.connect.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecare.connect.domain.HomeVisit;
import com.homecare.connect.domain.VisitStatus;
import com.homecare.connect.repository.CareNoteRepository;
import com.homecare.connect.repository.HomeVisitRepository;

@Service 
public class HomeVisitService {
    @Autowired 
    private HomeVisitRepository homeVisitRepository;

    @Autowired
    private CareNoteRepository careNoteRepository;

     public String createHomeVisit(HomeVisit homeVisit) {
        homeVisitRepository.save(homeVisit);
        return "Home visit saved successfully";
    }

    public List<HomeVisit> getAllHomeVisits() {
        return homeVisitRepository.findAll();
    }

    public Optional<HomeVisit> getByIdHomeVisit(UUID id) {
        return homeVisitRepository.findById(id);
    }

    public String updateHomeVisit(UUID id, HomeVisit homeVisit) {
        Optional<HomeVisit> allReady = homeVisitRepository.findById(id);
        if (allReady.isEmpty()) {
            return "The home visit with that id does not exist";
        }
        HomeVisit toUpdate = allReady.get();
        toUpdate.setVisitDate(homeVisit.getVisitDate());
        toUpdate.setStatus(homeVisit.getStatus());
        toUpdate.setCareType(homeVisit.getCareType());
        toUpdate.setHours(homeVisit.getHours());
        toUpdate.setCareRecipient(homeVisit.getCareRecipient());
        toUpdate.setCaregiver(homeVisit.getCaregiver());
        homeVisitRepository.save(toUpdate);
        return "Home visit updated successfully";
    }

   public String deleteHomeVisit(UUID id) {
    Optional<HomeVisit> already = homeVisitRepository.findById(id);
    if (already.isEmpty()) {
        return "The home visit with that id does not exist";
    }
    HomeVisit visit = already.get();
    if (careNoteRepository.existsByHomeVisit(visit)) {
        return "That home visit has care notes, delete them first";
    }
    homeVisitRepository.deleteById(id);
    return "Home visit deleted successfully";
    }

    public List<HomeVisit> getByStatus(VisitStatus status) {
    return homeVisitRepository.findByStatusOrderByVisitDateAsc(status);
    }

    public List<HomeVisit> getBetweenDates(LocalDate start, LocalDate end) {
    return homeVisitRepository.findByVisitDateBetweenOrderByVisitDateAsc(start, end);
    }
    public String changeStatus(UUID id, VisitStatus newStatus) {
    Optional<HomeVisit> already  = homeVisitRepository.findById(id);
    if (already.isEmpty()) {
        return "The home visit with that id does not exist";
    }
    HomeVisit visit = already.get();
    VisitStatus current = visit.getStatus();

    boolean accepted = false;
    if (current == VisitStatus.REQUESTED) {
        accepted = (newStatus == VisitStatus.ASSIGNED || newStatus == VisitStatus.DECLINED);
    } else if (current == VisitStatus.ASSIGNED) {
        accepted = (newStatus == VisitStatus.VISITED || newStatus == VisitStatus.DECLINED);
    }

    if (!accepted) {
        return "Cannot change status from " + current + " to " + newStatus;
    }

    visit.setStatus(newStatus);
    homeVisitRepository.save(visit);
    return "Status changed successfully";
    }

}
