package com.fst.projet_CarPooling_jee.Controller;

import com.fst.projet_CarPooling_jee.Entity.Reservation;
import com.fst.projet_CarPooling_jee.Entity.enums.ReservationStatus;
import com.fst.projet_CarPooling_jee.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Affiche la liste des réservations
    @GetMapping("/reservations")
    public String viewReservations(Model model) {
        model.addAttribute("listReservations", reservationService.getAllReservations());
        return "reservations"; // Vue HTML pour afficher les réservations
    }

    // Sauvegarde une nouvelle réservation
    @PostMapping("/saveReservation")
    public String saveReservation(@ModelAttribute("reservation") Reservation reservation) {
        reservationService.createReservation(reservation);
        return "redirect:/reservations"; // Redirection après la sauvegarde
    }

    // Mise à jour du statut d'une réservation
    @PostMapping("/updateReservationStatus")
    public String updateReservationStatus(
            @RequestParam("id") Long id,
            @RequestParam("status") ReservationStatus status
    ) {
        reservationService.updateReservationStatus(id, status);
        return "redirect:/reservations"; // Redirection après la mise à jour
    }
}
