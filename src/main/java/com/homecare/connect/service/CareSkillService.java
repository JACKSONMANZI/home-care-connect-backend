package com.homecare.connect.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homecare.connect.domain.CareSkill;
import com.homecare.connect.repository.CareSkillRepository;

@Service 
public class CareSkillService {
   @Autowired 
    private CareSkillRepository careSkillRepository;
   
   public String createCareSkill(CareSkill careSkill){
    careSkillRepository.save(careSkill);
    return "Care skill saved successfully";
   }
   public List<CareSkill> getAllCareSkills(){
    return careSkillRepository.findAll();
   }
   public Optional<CareSkill> getByIdCareSkill(UUID id){
    return careSkillRepository.findById(id);
   }
   public  String updateCareSkill(UUID id,CareSkill careSkill){
    Optional<CareSkill> existing=careSkillRepository.findById(id);
    if (existing.isEmpty()) {
        return "The care skill with that id does not exist";
        
    }
    CareSkill toUpdate = existing.get();
    toUpdate.setName(careSkill.getName());
    careSkillRepository.save(toUpdate);
    return "Care skill updated successfully";
   } 
   public String deleteCareSkill(UUID id){
    Optional<CareSkill> existing=careSkillRepository.findById(id);
    if (existing.isEmpty()) {
        return "The care skill with that id does not exist";
        
    }
    careSkillRepository.deleteById(id);
    return "Care skill deleted successfully";
   }   
   public List<CareSkill> getUnusedCareSkills() {
    return careSkillRepository.findUnusedCareSkills();
   }
}
