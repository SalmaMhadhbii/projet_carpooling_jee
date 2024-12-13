package com.fst.projet_CarPooling_jee.Repository;

import com.fst.projet_CarPooling_jee.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    // Find rides based on multiple filters
    List<Ride> findByStartLocationContainingAndEndLocationContainingAndDepartureDateAndAvailableSeatsGreaterThanEqual(
            String startLocation, String endLocation, Date departureDate, int availableSeats);

    // Find rides based on startLocation, endLocation, and departureTime
    List<Ride> findByStartLocationContainingAndEndLocationContainingAndDepartureDate(
            String startLocation, String endLocation, Date departureDate);

    // Find rides based on startLocation, endLocation, and availableSeats
    List<Ride> findByStartLocationContainingAndEndLocationContainingAndAvailableSeatsGreaterThanEqual(
            String startLocation, String endLocation, int availableSeats);

    // Find rides based on startLocation and endLocation
    List<Ride> findByStartLocationContainingAndEndLocationContaining(
            String startLocation, String endLocation);


}
