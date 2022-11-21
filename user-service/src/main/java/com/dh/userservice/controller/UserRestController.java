package com.dh.userservice.controller;

import com.dh.userservice.model.User;
import com.dh.userservice.model.UserNameDTO;
import com.dh.userservice.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/buscar/{id}")
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('GROUP_admin')")
    public List<UserNameDTO> findNonAdminUsers(){
        return userService.findAllNonAdminUsers();
    }

    @GetMapping("/admin/{username}")
    @PreAuthorize("hasAuthority('GROUP_admin')")
    public User findUserByUserName(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }

    @GetMapping("/buscarUsuario/{username}")
    public User findUserByName(@PathVariable("username") String username){
        return userService.findByUsername(username);

    }

}
