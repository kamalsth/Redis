package com.redis.practice.controllers;

import com.redis.practice.entities.User;
import com.redis.practice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@RequestMapping("/user")
public class UserController {
    private UserService userService;

//    @PostMapping("/save")
//   public ResponseEntity<?> saveUSer(@RequestBody User user){
//        return userService.save(user);
//    }


    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return userService.saveUser(user);

    }

    @GetMapping("/user")
    public ResponseEntity<?> fetchAllUser() {
        return userService.fetchAllUser();
    }



    //UserRepository

    @PostMapping("/addUser")
    public ResponseEntity<?>addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<?>getAllUsers(){
        return userService.getAllUsers();
    }
}

