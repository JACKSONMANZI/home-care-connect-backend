package com.homecare.connect.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homecare.connect.domain.HomeVisit;
import com.homecare.connect.domain.VisitStatus;
import com.homecare.connect.service.HomeVisitService;

@RestController 
@RequestMapping ("/api/home-visits")
public class HomeVisitController {
    @Autowired 
    private HomeVisitService homeVisitService;

     @PostMapping("/save")

    public ResponseEntity<String> createHomeVisit(@RequestBody HomeVisit homeVisit) {
        String show = homeVisitService.createHomeVisit(homeVisit);
        return ResponseEntity.status(HttpStatus.CREATED).body(show);
    }

    @GetMapping("/all")

    public ResponseEntity<List<HomeVisit>> getAllHomeVisits() {
        return ResponseEntity.status(HttpStatus.OK).body(homeVisitService.getAllHomeVisits());
    }

    @GetMapping("/by-status")

    public ResponseEntity<List<HomeVisit>> getByStatus(@RequestParam VisitStatus status) {
    return ResponseEntity.status(HttpStatus.OK).body(homeVisitService.getByStatus(status));
    }

    @GetMapping("/between")

    public ResponseEntity<List<HomeVisit>> getBetweenDates(@RequestParam String start, @RequestParam String end) {
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);
    return ResponseEntity.status(HttpStatus.OK).body(homeVisitService.getBetweenDates(startDate, endDate));
    }

    @PatchMapping("/{id}/status")

    public ResponseEntity<String> changeStatus(@PathVariable UUID id, @RequestParam VisitStatus status) {
    String show = homeVisitService.changeStatus(id, status);
    if (show.startsWith("Cannot change status")) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(show);
    } else if (show.equals("The home visit with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(show);
    } else {
        return ResponseEntity.status(HttpStatus.OK).body(show);
    }
    }

    @GetMapping("/{id}")
    
    public ResponseEntity<?> getByIdHomeVisit(@PathVariable UUID id) {
        Optional<HomeVisit> homeVisit = homeVisitService.getByIdHomeVisit(id);
        if (homeVisit.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(homeVisit.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The home visit with that id does not exist");
                    
        }
    }

    @PutMapping("/update/{id}")

    public ResponseEntity<String> updateHomeVisit(@PathVariable UUID id, @RequestBody HomeVisit homeVisit) {
        String show = homeVisitService.updateHomeVisit(id, homeVisit);
        if (show.equals("The home visit with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(show);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(show);
        }
    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<String> deleteHomeVisit(@PathVariable UUID id) {
        String show = homeVisitService.deleteHomeVisit(id);
        if (show.equals("The home visit with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(show);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(show);
        }
    }
}
