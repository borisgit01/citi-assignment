package com.borisgd.citi_assignment.controllers;

import com.borisgd.citi_assignment.domain.CAUser;
import com.borisgd.citi_assignment.services.CAUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CAUserController {

    private final CAUserService caUserService;

    public CAUserController(CAUserService caUserService) {
        this.caUserService = caUserService;
    }

    @GetMapping("/test")
    public void addUser() {
        caUserService.addFriend(1, 2);
    }

    @GetMapping("/users")
    public List<CAUser> getAllUsers() {
        return caUserService.getAllUsers();
    }

    @PostMapping("/user")
    public CAUser addNewUser(@RequestBody CAUser caUser) {
        return caUserService.addNewUser(caUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        caUserService.deleteById(id);
    }
}
