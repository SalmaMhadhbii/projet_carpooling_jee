package com.fst.projet_CarPooling_jee.Service.impl;

import com.fst.projet_CarPooling_jee.Entity.Ride;
import com.fst.projet_CarPooling_jee.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;

    public List<Ride> getAllRides(){
        return rideRepository.findAll();
    }

    public void saveRide(Ride ride){
        this.rideRepository.save(ride);
    }
}
