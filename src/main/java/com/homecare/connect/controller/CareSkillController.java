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

import com.homecare.connect.domain.CareSkill;
import com.homecare.connect.service.CareSkillService;

@RestController 
@RequestMapping("/api/care-skills") 
public class CareSkillController {
    @Autowired 
    private CareSkillService careSkillService;

    @PostMapping ("/save")

    public ResponseEntity<String> createCareSkill(@RequestBody CareSkill careSkill){
        String display = careSkillService.createCareSkill(careSkill);
        return ResponseEntity.status(HttpStatus.CREATED).body(display);
    }
    @GetMapping ("/all")

    public ResponseEntity<List<CareSkill>> getAllCareSkills(){
        return ResponseEntity.status(HttpStatus.OK).body(careSkillService.getAllCareSkills());
    }
    @GetMapping("/unused")

    public ResponseEntity<List<CareSkill>> getUnusedCareSkills() {  
         return ResponseEntity.status(HttpStatus.OK).body(careSkillService.getUnusedCareSkills());
}
    @GetMapping ("/{id}")

    public ResponseEntity<?> getByIdCareSkill(@PathVariable UUID id){
        Optional<CareSkill> careSkill = careSkillService.getByIdCareSkill(id);
        if(careSkill.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(careSkill.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The care skill with that id does not exist");
        }
    }
    @PutMapping ("/update/{id}")

    public ResponseEntity<String> updateCareSkill(@PathVariable UUID id,@RequestBody CareSkill careSkill){
        String display = careSkillService.updateCareSkill(id, careSkill);
        if (display.equals("The care skill with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(display);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(display);
        }
    }
    @DeleteMapping ("/delete/{id}")
    
    public  ResponseEntity<String> deleteCareSkill(@PathVariable UUID id){
        String display = careSkillService.deleteCareSkill(id);
        if (display.equals("The care skill with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(display);
        }
        else{
             return ResponseEntity.status(HttpStatus.OK).body(display);
        }
    }
}
