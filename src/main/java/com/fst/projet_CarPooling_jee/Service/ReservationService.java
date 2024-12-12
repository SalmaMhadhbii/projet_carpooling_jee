package com.fst.projet_CarPooling_jee.Service;

import com.fst.projet_CarPooling_jee.Entity.Reservation;
import com.fst.projet_CarPooling_jee.Entity.enums.ReservationStatus;

import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id);
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);
    Reservation updateReservationStatus(Long id, ReservationStatus status);
}
