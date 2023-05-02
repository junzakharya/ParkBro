package com.example.ParkBro.service.impl;

import com.example.ParkBro.enums.SpotType;
import com.example.ParkBro.model.ParkingLot;
import com.example.ParkBro.model.Spot;
import com.example.ParkBro.repository.ParkingLotRepository;
import com.example.ParkBro.repository.SpotRepository;
import com.example.ParkBro.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);

        return parkingLotRepository1.save(parkingLot);
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        Spot spot = new Spot();
        spot.setParkingLot(parkingLot);
        if(numberOfWheels == 2){
            spot.setSpotType(SpotType.TWO_WHEELER);
        } else if (numberOfWheels == 4) {
            spot.setSpotType(SpotType.FOUR_WHEELER);
        }
        else {
            spot.setSpotType(SpotType.OTHERS);
        }

        spot.setPricePerHour(pricePerHour);



        parkingLot.getSpotList().add(spot);
        parkingLotRepository1.save(parkingLot);

        return spotRepository1.save(spot);
    }

    @Override
    public void deleteSpot(int spotId) {

        Spot spot = spotRepository1.findById(spotId).get();
        spot.getReservationList().clear();
        spotRepository1.delete(spot);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        Spot spot = spotRepository1.findById(spotId).get();

        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        parkingLot.getSpotList().remove(spot);
        spot.setPricePerHour(pricePerHour);
        parkingLot.getSpotList().add(spot);

        return spotRepository1.save(spot);
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        parkingLot.getSpotList().clear();
        parkingLotRepository1.delete(parkingLot);
    }
}
