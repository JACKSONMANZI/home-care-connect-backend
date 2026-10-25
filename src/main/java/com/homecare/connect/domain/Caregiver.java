package com.homecare.connect.domain;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "caregiver")
public class Caregiver {
    @Id 
    @GeneratedValue 

    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne 
    @JoinColumn (name = "zone_id")
    private Zone zone;

    @ManyToMany 
    @JoinTable(name = "caregiver_care_skill",joinColumns=@JoinColumn (name="caregiver_id"),inverseJoinColumns=@JoinColumn (name="care_skill_id"))
    private List<CareSkill> careSkills;

    public Caregiver() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public List<CareSkill> getCareSkills() {
        return careSkills;
    }

    public void setCareSkills(List<CareSkill> careSkills) {
        this.careSkills = careSkills;
    }
    
}
