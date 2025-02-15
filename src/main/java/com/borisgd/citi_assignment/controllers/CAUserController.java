package com.borisgd.citi_assignment.controllers;

import com.borisgd.citi_assignment.domain.CAUser;
import com.borisgd.citi_assignment.domain.FriendActionResponse;
import com.borisgd.citi_assignment.services.CAUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CAUserController {

    private final CAUserService caUserService;

    public CAUserController(CAUserService caUserService) {
        this.caUserService = caUserService;
    }

    @DeleteMapping("/user/{userId}/friend/{friendId}")
    public FriendActionResponse removeFriend(@PathVariable Integer userId, @PathVariable Integer friendId) {
        return caUserService.removeFriend(userId, friendId);
    }

    @PostMapping("/user/{userId}/friend/{friendId}")
    public FriendActionResponse addFriend(@PathVariable Integer userId, @PathVariable Integer friendId) {
        return caUserService.addFriend(userId, friendId);
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
