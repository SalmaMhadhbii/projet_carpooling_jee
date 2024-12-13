package com.fst.projet_CarPooling_jee.Controller;

import com.fst.projet_CarPooling_jee.Entity.Ride;
import com.fst.projet_CarPooling_jee.Entity.User;
import com.fst.projet_CarPooling_jee.Repository.UserRepository;
import com.fst.projet_CarPooling_jee.Service.impl.RideService;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/showNewRidesForm")
    public String showNewRidesForm(Model model, HttpSession session){
        // Vérifier si l'utilisateur est connecté
        if (session.getAttribute("loggedInUserId") == null) {
            // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
            return "redirect:/loginn";
        }
        //create model attribute to bind form data
        //creation dun new ride:
        Ride ride = new Ride();
        model.addAttribute("ride", ride);
        //Thymleaf template will access the empty ride object for binding form data
        return "addRideForm";
    }

    // Save a new ride
    @PostMapping("/saveRide")
    public String saveRide(@ModelAttribute("ride") Ride ride,HttpSession session) {
        // Vérifier si l'utilisateur est connecté
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId"); // Assurez-vous que l'ID de l'utilisateur est stocké dans la session
        if (loggedInUserId != null) {
            // Trouver l'utilisateur dans la base de données par ID
            User driver = userRepository.findById(loggedInUserId).orElseThrow(() -> new RuntimeException("User not found"));

            // Associer l'utilisateur comme conducteur du trajet
            ride.setDriver(driver); // L'attribut "driver" est de type User, donc on lui assigne l'objet "User"
        } else {
            // Si l'utilisateur n'est pas connecté, rediriger vers la page de connexion
            return "redirect:/login"; // Assurez-vous que vous avez une page de connexion
        }
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
