package com.example.ParkBro.dto;

import com.example.ParkBro.enums.SpotType;
import com.example.ParkBro.model.ParkingLot;
import com.example.ParkBro.model.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parking_lot")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpotResponseDto {
    int parkingLotId;

    SpotType spotType;

    int pricePerHour;

    Boolean occupied;

    List<ReservationResponseDto> reservationList;
}
