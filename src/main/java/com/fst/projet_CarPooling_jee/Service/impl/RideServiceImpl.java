package com.fst.projet_CarPooling_jee.Service.impl;

import com.fst.projet_CarPooling_jee.Entity.Ride;
import com.fst.projet_CarPooling_jee.Repository.RideRepository;
import com.fst.projet_CarPooling_jee.Service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideServiceImpl implements RideService {
    @Autowired
    private RideRepository rideRepository;

    @Override //Indique une redéfinition de la méthode
    public List<Ride> getAllRides(){
        return rideRepository.findAll();
    }

    //ajouter un trajet
    public void saveRide(Ride ride){
        this.rideRepository.save(ride);
    }
}
