package com.dico.MalariaDataApp.user.controller;

import com.dico.MalariaDataApp.user.model.Role;
import com.dico.MalariaDataApp.user.service.RoleService;
import com.dico.MalariaDataApp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/security/roles")
    public String parameters(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "security/roles";
    }

    @GetMapping("/security/roleEdit/{id}")
    public String editRole(@PathVariable Integer id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("userRole", role);
        return "security/roleEdit";
    }
    @GetMapping("/security/addRole")
    public String addRole() {
        return "/security/addRole";
    }

    @PostMapping("/security/addRole")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:/security/roles";
    }
    //The op parameter is either Edit or Details
//    @GetMapping("/user/role/{id}")
//    public String editRole(@PathVariable Integer id, Model model){
//        Role role = roleService.findById(id);
//        model.addAttribute("roles", role);
//        return "/parameters/country";
//    }

    @RequestMapping(value = "/security/role/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        roleService.delete(id);
        return "redirect:/security/roles";
    }

    @RequestMapping("/security/role/assign/{userId}/{roleId}")
    public String assignRole(@PathVariable Integer userId,
                             @PathVariable Integer roleId) {
        roleService.assignUserRole(userId, roleId);
        return "redirect:/security/userEdit/" + userId;
    }

    @RequestMapping("/security/role/unassign/{userId}/{roleId}")
    public String unassignRole(@PathVariable Integer userId,
                               @PathVariable Integer roleId) {
        roleService.unassignUserRole(userId, roleId);
        return "redirect:/security/userEdit/" + userId;
    }
}
