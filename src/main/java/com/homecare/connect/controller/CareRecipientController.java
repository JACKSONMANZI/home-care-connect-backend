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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homecare.connect.domain.CareRecipient;
import com.homecare.connect.service.CareRecipientService;

@RestController 
@RequestMapping ("/api/care-recipients")
public class CareRecipientController {
    @Autowired 
    private CareRecipientService careRecipientService;

    @PostMapping("/save")

    public ResponseEntity<String> createCareRecipient(@RequestBody CareRecipient careRecipient) {
        String show = careRecipientService.createCareRecipient(careRecipient);
        return ResponseEntity.status(HttpStatus.CREATED).body(show);
    }

    @GetMapping("/all")

    public ResponseEntity<List<CareRecipient>> getAllCareRecipients() {
        return ResponseEntity.status(HttpStatus.OK).body(careRecipientService.getAllCareRecipients());
    }

        @GetMapping("/by-last-name")
    
public ResponseEntity<List<CareRecipient>> getByLastName(@RequestParam String lastName) {
    return ResponseEntity.status(HttpStatus.OK).body(careRecipientService.getByLastName(lastName));
}

    @GetMapping("/{id}")

    public ResponseEntity<?> getByIdCareRecipient(@PathVariable UUID id) {
        Optional<CareRecipient> careRecipient = careRecipientService.getByIdCareRecipient(id);
        if (careRecipient.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(careRecipient.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The care recipient with that id does not exist");
                    
        }
    }

    @PutMapping("/update/{id}")

    public ResponseEntity<String> updateCareRecipient(@PathVariable UUID id, @RequestBody CareRecipient careRecipient) {
        String show = careRecipientService.updateCareRecipient(id, careRecipient);
        if (show.equals("The care recipient with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(show);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(show);
        }
    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<String> deleteCareRecipient(@PathVariable UUID id) {
        String show = careRecipientService.deleteCareRecipient(id);
        if (show.equals("The care recipient with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(show);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(show);
        }
    }
}
