package com.homecare.connect.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecare.connect.domain.Zone;
import com.homecare.connect.repository.ZoneRepository;

@Service 
public class ZoneService {
    @Autowired 
    private ZoneRepository zoneRepository;
    
    public String createZone(Zone zone){
        zoneRepository.save(zone);
        return "Zone saved successfully";
    }
    public List<Zone> getAllZone(){
        return zoneRepository.findAll();
    }
    public Optional<Zone> getByIdZone(UUID id){
        return zoneRepository.findById(id);
    }
    public String updateZone(UUID id,Zone zone){
        Optional<Zone> existing = zoneRepository.findById(id);
        if (existing.isEmpty()) {
            return "The zone with that id does not exist";
        }
        Zone toUpdate = existing.get();
        toUpdate.setZoneNumber(zone.getZoneNumber());
        toUpdate.setName(zone.getName());
        zoneRepository.save(toUpdate);
        return "Zone updated successfully";
    }
    public String deleteZone(UUID id){
        Optional<Zone> existing = zoneRepository.findById(id);
        if (existing.isEmpty()) {
            return "The zone with that id does not exist";
        }
        zoneRepository.deleteById(id);
        return "Zone deleted successfully";
    }
}
