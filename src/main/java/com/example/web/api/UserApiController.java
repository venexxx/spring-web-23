package com.example.web.api;

import com.example.model.dto.UserBindingModel;
import com.example.model.entity.UserEntity;
import com.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/details")
public class UserApiController {


    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserBindingModel> getUserById(@PathVariable Long id) {
        UserBindingModel user = userService.getById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Other methods for creating, updating, and deleting users
}