package com.example.ParkBro.service.impl;

import com.example.ParkBro.enums.PaymentMode;
import com.example.ParkBro.model.Payment;
import com.example.ParkBro.model.Reservation;
import com.example.ParkBro.repository.PaymentRepository;
import com.example.ParkBro.repository.ReservationRepository;
import com.example.ParkBro.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        Reservation reservation = reservationRepository2.findById(reservationId).get();

        if (!mode.equalsIgnoreCase("cash") && !mode.equalsIgnoreCase("card") && !mode.equalsIgnoreCase("upi")) {
            reservationRepository2.deleteById(reservationId);
            throw new Exception("Payment mode not detected");
        }

        int amountToPay = reservation.getSpot().getPricePerHour() * reservation.getNumberOfHours();

        if(amountSent<amountToPay){
            reservationRepository2.deleteById(reservationId);
            throw new Exception("Insufficient Amount");
        }

        Payment payment = new Payment();
        payment.setPaymentMode(PaymentMode.valueOf(mode));
        payment.setIsPaymentCompleted(true);
        payment.setReservation(reservation);

        return paymentRepository2.save(payment);
    }
}
