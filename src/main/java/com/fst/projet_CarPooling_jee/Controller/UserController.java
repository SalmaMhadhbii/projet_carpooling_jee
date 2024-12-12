package com.fst.projet_CarPooling_jee.Controller;

import com.fst.projet_CarPooling_jee.Entity.User;
import com.fst.projet_CarPooling_jee.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    //inject service class
    @Autowired
    private UserService userService;

    //display list of users
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<User> users = userService.getAllUsers(); // Récupérer la liste des utilisateurs depuis le service
        model.addAttribute("listUsers", users); // Ajouter les utilisateurs au modèle
        return "index";
    }
    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model) {
        //create model attribute to bind from data
        User user = new User();
        //Thymeleaf template will access the empty user object binding form data
        model.addAttribute("user", user);
        return "new_user";
    }

    // Page de connexion (loginn)
    @GetMapping("/loginn")
    public String loginn() {
        return "loginn"; // Affiche la page de connexion
    }
    @GetMapping("/AllUsers")
    public String viewDrivers(Model model) {
        List<User> drivers = userService.getAllUsers();
        model.addAttribute("drivers", drivers);
        return "AllUsers";
    }

}