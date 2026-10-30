package com.homecare.connect.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecare.connect.domain.Caregiver;
import com.homecare.connect.repository.CaregiverRepository;

@Service 
public class CaregiverService {
     @Autowired
    private CaregiverRepository caregiverRepository;

    public String createCaregiver(Caregiver caregiver) {
        caregiverRepository.save(caregiver);
        return "Caregiver saved successfully";
    }

    public List<Caregiver> getAllCaregivers() {
        return caregiverRepository.findAll();
    }

    public Optional<Caregiver> getByIdCaregiver(UUID id) {
        return caregiverRepository.findById(id);
    }

    public String updateCaregiver(UUID id, Caregiver caregiver) {
        Optional<Caregiver> allReady = caregiverRepository.findById(id);
        if (allReady.isEmpty()) {
            return "The caregiver with that id does not exist";
        }
        Caregiver toUpdate = allReady.get();
        toUpdate.setFirstName(caregiver.getFirstName());
        toUpdate.setLastName(caregiver.getLastName());
        toUpdate.setPhone(caregiver.getPhone());
        caregiverRepository.save(toUpdate);
        return "Caregiver updated successfully";
    }

    public String deleteCaregiver(UUID id) {
        Optional<Caregiver> allReady = caregiverRepository.findById(id);
        if (allReady.isEmpty()) {
            return "The caregiver with that id does not exist";
        }
        caregiverRepository.deleteById(id);
        return "Caregiver deleted successfully";
    }
    public List<Caregiver> getByCareSkillName(String name) {
    return caregiverRepository.findByCareSkillName(name);
}
}
