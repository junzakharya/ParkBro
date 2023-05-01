package com.example.ParkBro.service.impl;

import com.example.ParkBro.enums.PaymentMode;
import com.example.ParkBro.enums.SpotType;
import com.example.ParkBro.model.*;
import com.example.ParkBro.repository.ParkingLotRepository;
import com.example.ParkBro.repository.ReservationRepository;
import com.example.ParkBro.repository.SpotRepository;
import com.example.ParkBro.repository.UserRepository;
import com.example.ParkBro.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Autowired
    PaymentServiceImpl paymentService;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {

        User user = userRepository3.findById(userId).orElseThrow(() -> new Exception("Cannot make reservation - user not found"));
        ParkingLot parkingLot = parkingLotRepository3.findById(parkingLotId).orElseThrow(() -> new Exception("Cannot make reservation -  parking lot not found"));

        String spotType = findSpotType(numberOfWheels);

        List<Spot> availableSpots = spotRepository3.findAvailableSpot(parkingLot, spotType);

        int minimumPrice = Integer.MAX_VALUE;
        Spot minimumPriceSpot = null;
        for (Spot spot : availableSpots) {
            int totalPrice = spot.getPricePerHour() * timeInHours;
            if (totalPrice < minimumPrice) {
                minimumPrice = totalPrice;
                minimumPriceSpot = spot;
            }
        }

        if (minimumPriceSpot != null){
            Reservation reservation = new Reservation();
            reservation.setSpot(minimumPriceSpot);
            reservation.setUser(user);
            reservation.setNumberOfHours(timeInHours);

            reservationRepository3.save(reservation);


            minimumPriceSpot.getReservationList().add(reservation);
            minimumPriceSpot.setOccupied(true);

            spotRepository3.save(minimumPriceSpot);

            return reservation;
        }
        else{
            throw new Exception("Cannot make reservation - no spots available for given vehicle type");
        }

    }

    public String findSpotType(Integer numberOfWheels){
        SpotType spotType;
        if(numberOfWheels <= 2){
            spotType= SpotType.TWO_WHEELER;
        } else if (numberOfWheels <= 4) {
            spotType = SpotType.FOUR_WHEELER;
        }
        else spotType = SpotType.OTHERS;

        return String.valueOf(spotType);
    }
}
