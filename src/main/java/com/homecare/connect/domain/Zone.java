package com.homecare.connect.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "zone")
public class Zone {
  @Id 
  @GeneratedValue 
  private UUID id;
  private int zoneNumber;
  private String name;
  public Zone() {
  }
  public UUID getId() {
    return id;
  }
  public void setId(UUID id) {
    this.id = id;
  }
  public int getZoneNumber() {
    return zoneNumber;
  }
  public void setZoneNumber(int zoneNumber) {
    this.zoneNumber = zoneNumber;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
}
