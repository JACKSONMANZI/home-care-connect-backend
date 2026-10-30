package com.homecare.connect.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.homecare.connect.domain.Zone;

public interface ZoneRepository extends JpaRepository<Zone,UUID> {
    
    @Query("SELECT z FROM Zone z WHERE NOT EXISTS (SELECT c FROM Caregiver c WHERE c.zone = z) ORDER BY z.zoneNumber")
    List<Zone> findFreeZones();
    
    Optional<Zone> findByZoneNumber(int zoneNumber);
}
