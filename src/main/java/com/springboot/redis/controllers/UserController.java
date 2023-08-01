package com.springboot.redis.controllers;


import com.springboot.redis.entities.User;
import com.springboot.redis.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<?>getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("getUserById")
    public ResponseEntity<?> getUserById(@RequestParam(name = "userId")Long id){
        return userService.getUserById(id);
    }

    @PutMapping("updateUser")
    public ResponseEntity<?> updateUser(@RequestParam(name = "userId")Long id,@RequestBody User user){
        return userService.updatedUser(id,user);
    }

    @DeleteMapping("deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam(name = "userId")Long id){
        return userService.deleteUser(id);
    }
}
