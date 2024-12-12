package com.fst.projet_CarPooling_jee.Service.impl;

import com.fst.projet_CarPooling_jee.Entity.Ride;
import com.fst.projet_CarPooling_jee.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;

    // Get all rides
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    // Save a ride to the database
    public void saveRide(Ride ride) {
        this.rideRepository.save(ride);
    }

    // Find rides based on search criteria
    public List<Ride> findRides(String startLocation, String endLocation, Date departureDate, Integer nbPassengers) {
        // Return rides filtered based on given parameters
        if (departureDate != null && nbPassengers != null) {
            return rideRepository.findByStartLocationContainingAndEndLocationContainingAndDepartureDateAndAvailableSeatsGreaterThanEqual(
                    startLocation, endLocation, departureDate, nbPassengers);
        } else if (departureDate != null) {
            return rideRepository.findByStartLocationContainingAndEndLocationContainingAndDepartureDate(
                    startLocation, endLocation, departureDate);
        } else if (nbPassengers != null) {
            return rideRepository.findByStartLocationContainingAndEndLocationContainingAndAvailableSeatsGreaterThanEqual(
                    startLocation, endLocation, nbPassengers);
        } else {
            return rideRepository.findByStartLocationContainingAndEndLocationContaining(
                    startLocation, endLocation);
        }
    }
}
