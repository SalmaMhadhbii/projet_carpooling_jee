package com.fst.projet_CarPooling_jee.Service.impl;

import com.fst.projet_CarPooling_jee.Entity.Reservation;
import com.fst.projet_CarPooling_jee.Entity.enums.ReservationStatus;
import com.fst.projet_CarPooling_jee.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }


    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + id));
    }


    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Reservation existingReservation = getReservationById(id);

        existingReservation.setNbReservedPlaces(updatedReservation.getNbReservedPlaces());
        existingReservation.setStatus(updatedReservation.getStatus());
        existingReservation.setUser(updatedReservation.getUser());
        existingReservation.setRide(updatedReservation.getRide());

        return reservationRepository.save(existingReservation);
    }


    public void deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }


    public Reservation updateReservationStatus(Long id, ReservationStatus status) {
        Reservation reservation = getReservationById(id);
        reservation.setStatus(status);
        return reservationRepository.save(reservation);
    }
}
