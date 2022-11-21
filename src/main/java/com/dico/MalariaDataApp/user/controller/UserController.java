package com.dico.MalariaDataApp.user.controller;

import com.dico.MalariaDataApp.user.model.User;
import com.dico.MalariaDataApp.user.service.RoleService;
import com.dico.MalariaDataApp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    //Index route
//    @GetMapping("")
//    public String getHome(){
//        return "index";
//    }

    //Login route
    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    //logout route
    @GetMapping("/logout")
    public String logout(){
        return "/login";
    }

    @GetMapping("/security/role")
    public String UserRoles(){
        return "/security/role";
    }


    @GetMapping("/security/addUser")
    public String addUser(){
        return "/security/register";
    }

    //Get all users route
    @GetMapping("/security/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "/security/users";
    }


    //Get single user by Id
    @GetMapping("/security/userEdit/{id}")
    public String getSingleUser(@PathVariable Integer id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("userRoles", roleService.getUserRoles(user));
        model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
        return "/security/userEdit";
    }
    //Add New User
    @PostMapping("/security/addUser")
    public RedirectView addUser(User user, RedirectAttributes redir) {
        userService.save(user);
        RedirectView redirectView = new RedirectView("/login", true);
        redir.addFlashAttribute("message", "You have successfully registered a new user!");
        return redirectView;
    }

}
