package com.example.ParkBro.service;

import com.example.ParkBro.model.Payment;

public interface PaymentService {
    Payment pay(Integer reservationId, int amountSent, String mode) throws Exception;
}
