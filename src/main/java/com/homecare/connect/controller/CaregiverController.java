package com.homecare.connect.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homecare.connect.domain.Caregiver;
import com.homecare.connect.service.CaregiverService;

@RestController 
@RequestMapping ("/api/caregivers")
public class CaregiverController {
    @Autowired 
    private CaregiverService caregiverService;

      @PostMapping("/save")

    public ResponseEntity<String> createCaregiver(@RequestBody Caregiver caregiver) {
        String show = caregiverService.createCaregiver(caregiver);
        return ResponseEntity.status(HttpStatus.CREATED).body(show);
    }

    @GetMapping("/all")

    public ResponseEntity<List<Caregiver>> getAllCaregivers() {
        return ResponseEntity.status(HttpStatus.OK).body(caregiverService.getAllCaregivers());
    }

    @GetMapping("/{id}")

    public ResponseEntity<?> getByIdCaregiver(@PathVariable UUID id) {
        Optional<Caregiver> caregiver = caregiverService.getByIdCaregiver(id);
        if (caregiver.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(caregiver.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The caregiver with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")

    public ResponseEntity<String> updateCaregiver(@PathVariable UUID id, @RequestBody Caregiver caregiver) {
        String message = caregiverService.updateCaregiver(id, caregiver);
        if (message.equals("The caregiver with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<String> deleteCaregiver(@PathVariable UUID id) {
        String show = caregiverService.deleteCaregiver(id);
        if (show.equals("The caregiver with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(show);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(show);
        }
    }
}
