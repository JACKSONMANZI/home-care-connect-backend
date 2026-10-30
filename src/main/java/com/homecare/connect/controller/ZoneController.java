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

import com.homecare.connect.domain.Zone;
import com.homecare.connect.service.ZoneService;

@RestController 
@RequestMapping ("/api/zones")
public class ZoneController {
    @Autowired 
    private ZoneService zoneService;

    @PostMapping ("/save")
    public ResponseEntity<String> createZone(@RequestBody Zone zone){
        String show = zoneService.createZone(zone);
        return ResponseEntity.status(HttpStatus.CREATED).body(show);
    }
    @GetMapping ("/all")
    
    public ResponseEntity<List<Zone>> getAllZone(){
        return ResponseEntity.status(HttpStatus.OK).body(zoneService.getAllZone());
    }

    @GetMapping("/free")

    public ResponseEntity<List<Zone>> getFreeZones() {
    return ResponseEntity.status(HttpStatus.OK).body(zoneService.getFreeZones());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getByIdZone(@PathVariable UUID id){
        Optional<Zone> zone = zoneService.getByIdZone(id);
        if (zone.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(zone.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The zone with that id does not exist");
        }
    }
    @PutMapping ("/update/{id}")
    public ResponseEntity<String> updateZone(@PathVariable UUID id,@RequestBody Zone zone){
        String tell = zoneService.updateZone(id, zone);
        if (tell.equals("The zone with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tell);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(tell);
        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteZone(@PathVariable UUID id){
        String tell = zoneService.deleteZone(id);
        if (tell.equals("The zone with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tell);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(tell);
        }
    }
}
