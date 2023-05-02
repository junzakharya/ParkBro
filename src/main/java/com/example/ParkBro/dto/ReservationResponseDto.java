package com.example.ParkBro.dto;

import com.example.ParkBro.model.Payment;
import com.example.ParkBro.model.Spot;
import com.example.ParkBro.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_lot")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {
    int numberOfHours;

    int userId;

    int spotId;

    int paymentId;
}
