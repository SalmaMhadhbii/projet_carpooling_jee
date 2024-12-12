package com.fst.projet_CarPooling_jee.Controller;

import com.fst.projet_CarPooling_jee.Entity.Ride;
import com.fst.projet_CarPooling_jee.Service.impl.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

@Controller
public class RideController {

    private static final Logger logger = LoggerFactory.getLogger(RideController.class);

    @Autowired
    private RideService rideService;

    // Show the form to add a new ride
    @GetMapping("/showNewRideForm")
    public String showNewRideForm(Model model) {
        model.addAttribute("ride", new Ride());
        return "addRideForm";
    }

    // Save a new ride
    @PostMapping("/saveRide")
    public String saveRide(@ModelAttribute("ride") Ride ride) {
        rideService.saveRide(ride);
        return "redirect:/searchRides"; // Redirect to search page after saving
    }

    // Search for rides based on filters
    @GetMapping("/searchRides")
    public String searchRides(
            @RequestParam(value = "startLocation", required = false) String startLocation,
            @RequestParam(value = "endLocation", required = false) String endLocation,
            @RequestParam(value = "departureDate", required = false) String departureDate,
            @RequestParam(value = "nbPassengers", required = false) Integer nbPassengers,
            Model model) {

        logger.info("Search parameters received: startLocation={}, endLocation={}, departureDate={}, nbPassengers={}",
                startLocation, endLocation, departureDate, nbPassengers);

        boolean searchPerformed = (startLocation != null && !startLocation.isEmpty()) ||
                (endLocation != null && !endLocation.isEmpty()) ||
                (departureDate != null && !departureDate.isEmpty()) ||
                (nbPassengers != null);

        List<Ride> listRides = null;

        // Perform search only if at least one parameter is provided
        if (searchPerformed) {
            Date sqlDate = null;
            if (departureDate != null && !departureDate.isEmpty()) {
                try {
                    sqlDate = Date.valueOf(departureDate); // Convert String to SQL Date
                } catch (IllegalArgumentException e) {
                    model.addAttribute("error", "Invalid date format. Use yyyy-MM-dd.");
                    return "searchRides"; // Return to search page with error
                }
            }
            listRides = rideService.findRides(startLocation, endLocation, sqlDate, nbPassengers);
        }

        model.addAttribute("searchPerformed", searchPerformed);
        model.addAttribute("listRides", listRides);

        return "searchRides"; // Thymeleaf template to render
    }
}
