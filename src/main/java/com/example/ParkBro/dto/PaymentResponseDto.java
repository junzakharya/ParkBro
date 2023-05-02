package com.example.ParkBro.dto;

import com.example.ParkBro.enums.PaymentMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
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
public class PaymentResponseDto {

    int reservationId;
    Boolean isPaymentCompleted;

    PaymentMode paymentMode;
}
