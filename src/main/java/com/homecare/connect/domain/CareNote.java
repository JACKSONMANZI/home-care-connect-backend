package com.homecare.connect.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "care_note") 
public class CareNote {
    
    @Id 
    @GeneratedValue 

    private UUID id;
    private String activityName;
    private String observations;
    private int durationMinutes;
    private LocalDate notedDate;

    @ManyToOne 
    @JoinColumn (name = "home_visit_id")
    private HomeVisit homeVisit;

    public CareNote() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalDate getNotedDate() {
        return notedDate;
    }

    public void setNotedDate(LocalDate notedDate) {
        this.notedDate = notedDate;
    }

    public HomeVisit getHomeVisit() {
        return homeVisit;
    }

    public void setHomeVisit(HomeVisit homeVisit) {
        this.homeVisit = homeVisit;
    }
    
}
