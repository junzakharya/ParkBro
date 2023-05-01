package com.example.ParkBro.repository;

import com.example.ParkBro.model.ParkingLot;
import com.example.ParkBro.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer> {

    @Query(value = "select * from spot s where s.parking_lot =:parkingLot and s.spot_type =:spotType", nativeQuery = true)
    List<Spot> findAvailableSpot(ParkingLot parkingLot, String spotType);
}
