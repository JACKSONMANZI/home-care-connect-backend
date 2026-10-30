package com.homecare.connect.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecare.connect.domain.CareRecipient;
import com.homecare.connect.repository.CareRecipientRepository;

@Service 
public class CareRecipientService {
    @Autowired 
    private CareRecipientRepository careRecipientRepository;

    public String createCareRecipient(CareRecipient careRecipient) {
        careRecipientRepository.save(careRecipient);
        return "Care recipient saved successfully";
    }

    public List<CareRecipient> getAllCareRecipients() {
        return careRecipientRepository.findAll();
    }

    public Optional<CareRecipient> getByIdCareRecipient(UUID id) {
        return careRecipientRepository.findById(id);
    }

    public String updateCareRecipient(UUID id, CareRecipient careRecipient) {
        Optional<CareRecipient> allReady = careRecipientRepository.findById(id);
        if (allReady.isEmpty()) {
            return "The care recipient with that id does not exist";
        }
        CareRecipient toUpdate = allReady.get();
        toUpdate.setFirstName(careRecipient.getFirstName());
        toUpdate.setLastName(careRecipient.getLastName());
        toUpdate.setNationalId(careRecipient.getNationalId());
        toUpdate.setPhone(careRecipient.getPhone());
        toUpdate.setAddress(careRecipient.getAddress());
        careRecipientRepository.save(toUpdate);
        return "Care recipient updated successfully";
    }

    public String deleteCareRecipient(UUID id) {
        Optional<CareRecipient> allReady = careRecipientRepository.findById(id);
        if (allReady.isEmpty()) {
            return "The care recipient with that id does not exist";
        }
        careRecipientRepository.deleteById(id);
        return "Care recipient deleted successfully";
    }
    public List<CareRecipient> getByLastName(String lastName) {
    return careRecipientRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
}
}
