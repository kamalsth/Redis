package com.redis.practice.services;

import com.redis.practice.entities.User;
import com.redis.practice.repositories.Repo;
import com.redis.practice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private Repo repo;
    private UserRepository userRepository;
    private RedisTemplate redisTemplate;
    private static final String KEY = "USER";

//    @Autowired
//    private RedisTemplate template;
//    public static final String Hash_KEY = "user";
//     private final UserRepository userRepository;

//
//    public ResponseEntity<?> save(User user) {
//        // return userRepository.save(user);
////        template.opsForHash().put(Hash_KEY, user.getUserId(), user);
////        userRepository.save(user);
//       user.setAddress("kathmandu");
//       user.setName("kamal");
//       return ResponseEntity.ok("user added");
//    }

    public ResponseEntity<?>addUser(User user){
        user.setName(user.getName());
        user.setAddress(user.getAddress());
        redisTemplate.opsForHash().put(KEY, user.getUserId().toString(), user);

        // userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?>getAllUsers(){
        return ResponseEntity.ok(redisTemplate.opsForHash().values(KEY));
    }

    public ResponseEntity<?> saveUser(User user) {
        boolean result = repo.saveUser(user);
        if(result)
            return ResponseEntity.ok("User Created Successfully!!");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

//        return repo.saveUser(user);
    }

    public ResponseEntity<?> fetchAllUser() {
        List<User> users=repo.fetchAllUser();
        return ResponseEntity.ok(users);
    }
}
