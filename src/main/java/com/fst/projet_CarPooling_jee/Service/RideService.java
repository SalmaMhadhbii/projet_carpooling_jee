package com.fst.projet_CarPooling_jee.Service;

import com.fst.projet_CarPooling_jee.Entity.Ride;

import java.util.List;

public interface RideService {
    List<Ride> getAllRides();
    void saveRide(Ride ride);
}
