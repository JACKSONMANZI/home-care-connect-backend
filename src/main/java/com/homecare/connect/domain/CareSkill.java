package com.homecare.connect.domain;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "care_skill")
public class CareSkill {
    @Id 
    @GeneratedValue 
    private UUID id;
    private String name;
     
   @ManyToMany (mappedBy = "careSkills")
   @JsonIgnore 
   private List<Caregiver> caregivers;

   public CareSkill() {
   }

   public UUID getId() {
    return id;
   }

   public void setId(UUID id) {
    this.id = id;
   }

   public String getName() {
    return name;
   }

   public void setName(String name) {
    this.name = name;
   }

   public List<Caregiver> getCaregivers() {
    return caregivers;
   }

   public void setCaregivers(List<Caregiver> caregivers) {
    this.caregivers = caregivers;
   }
   
  
}
