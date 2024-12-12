package com.fst.projet_CarPooling_jee.Controller;

import com.fst.projet_CarPooling_jee.Entity.Ride;
import com.fst.projet_CarPooling_jee.Service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RideController {
    @Autowired
    private RideService rideService; /*ici on a utilisé
    une interface non pas une classe RideServiceImpl
    car si on utilose une classe on doit faire une instance
    (new)à chaque execution en plus avec l'interface on
    peut acceder à tous ses classes d'implementation*/

    //display list of ride
    @GetMapping("/searchRides") /*si un utilisateur accède
     à la page d'accueil, cette méthode récupère la
      liste des trajets (rides) depuis la base
       de données et les transmet à la vue (HTML).
     */
    //viewRides: methode handler qui repond à une req http
    public String viewRides(Model model) {
        model.addAttribute("listRides",rideService.getAllRides());
        return "searchRides";
        /*model un objet qui permet de passer des données
         depuis le contrôleur jusqu'à la vue */
    }
    @GetMapping("/showNewRideForm")
    public String showNewRideForm(Model model) {
        Ride ride = new Ride();
        model.addAttribute("ride",ride);
        return "addRideForm";
    }

    @PostMapping("/saveRide")
    public String SaveRide(@ModelAttribute("ride") Ride ride){
        //save ride to database
        rideService.saveRide(ride);
        return "newRide";
    }

    /*@GetMapping("/saveRide")
    public String redirectToForm() {
        return "redirect:/showNewRideForm";
    }*/


}
