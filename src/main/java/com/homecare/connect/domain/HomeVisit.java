package com.homecare.connect.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "home_visit")
public class HomeVisit {
    @Id 
    @GeneratedValue 
    
    private UUID id;
    private LocalDate visitDate;
    @Enumerated (EnumType.STRING)
    private VisitStatus status;
    private String careType;
    private int hours;

    @ManyToOne 
    @JoinColumn (name = "care_recipient_id")
    private CareRecipient careRecipient;

    @ManyToOne 
    @JoinColumn (name = "caregiver_id")
    private Caregiver caregiver;

    public HomeVisit() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }

    public String getCareType() {
        return careType;
    }

    public void setCareType(String careType) {
        this.careType = careType;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public CareRecipient getCareRecipient() {
        return careRecipient;
    }

    public void setCareRecipient(CareRecipient careRecipient) {
        this.careRecipient = careRecipient;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }
    
}
