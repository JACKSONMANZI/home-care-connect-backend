package com.homecare.connect.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homecare.connect.domain.CareNote;
import com.homecare.connect.service.CareNoteService;

@RestController 
@RequestMapping ("/api/care-notes")
public class CareNoteController {
    @Autowired 
    private CareNoteService careNoteService;

     @PostMapping("/save")
     public ResponseEntity<String> createCareNote(@RequestParam UUID homeVisitId, @RequestBody CareNote careNote) {
        String show = careNoteService.createCareNote(homeVisitId, careNote);
        if (show.equals("The home visit with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(show);
        } else if (show.equals("Care notes are only for VISITED home visits")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(show);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(show);
        }
    }

    @GetMapping("/care-recipient/{nationalId}")
    public ResponseEntity<List<CareNote>> getCareNotesByRecipientNationalId(@PathVariable String nationalId) {
        return ResponseEntity.status(HttpStatus.OK).body(careNoteService.getCareNotesByRecipientNationalId(nationalId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCareNote(@PathVariable UUID id) {
        String show = careNoteService.deleteCareNote(id);
        if (show.equals("The care note with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(show);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(show);
        }
    }
}
