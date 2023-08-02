package com.springboot.redis.services;

import com.springboot.redis.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private static final String HASH_KEY = "USER";
    private RedisTemplate<String,Object> redisTemplate;

    public ResponseEntity<?> addUser(User user) {
        if (redisTemplate.opsForHash().hasKey(HASH_KEY, user.getUserId())) {
            return ResponseEntity.badRequest().body("User with ID: " + user.getUserId() + " is already present.");
        }
        redisTemplate.opsForHash().put(HASH_KEY, user.getUserId(), user);
        return ResponseEntity.ok(user);
    }


    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(redisTemplate.opsForHash().values(HASH_KEY));
    }


    public ResponseEntity<?> getUserById(Long id) {
        if (redisTemplate.opsForHash().hasKey(HASH_KEY, id)) {
            User user = (User) redisTemplate.opsForHash().get(HASH_KEY, id);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(404).body("User not found with ID: " + id);
    }


    public ResponseEntity<?> updatedUser(Long id, User user) {
        if (redisTemplate.opsForHash().hasKey(HASH_KEY, id)) {
            redisTemplate.opsForHash().put(HASH_KEY, id, user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(404).body("User not found with ID: " + id);
    }



    public ResponseEntity<?> deleteUser(Long id) {
        if (redisTemplate.opsForHash().hasKey(HASH_KEY, id)) {
            redisTemplate.opsForHash().delete(HASH_KEY, id);
            return ResponseEntity.ok("User is deleted");
        }
        return ResponseEntity.status(404).body("User not found with ID: " + id);
    }


}
