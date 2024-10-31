package com.adison.crud1033.controllers;

import com.adison.crud1033.entity.User;
import com.adison.crud1033.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
   private UserService userService;

   @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public User addUser(@RequestBody User user){
       user.setId(0);
        if (user.getRole() == null ) {
            user.setRole(User.Role.user); // ตั้งค่า role เป็น CUSTOMER หากไม่มีการกรอก
        }
       return  userService.save(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticateUser(@RequestBody User user) {
        User authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticatedUser == null) {
            return ResponseEntity.status(401).body(new User() {{
                setMessage("Invalid username or password");
                setResponseRole("");
            }});
        }
        authenticatedUser.setMessage("Login successful!!");
        authenticatedUser.setResponseRole(authenticatedUser.getRole().name());
        return ResponseEntity.ok(authenticatedUser);
    }

    @GetMapping
    public List<User> getAlluser(){
       return  userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
       User myUser = userService.findById(id);
        if (myUser==null) {
            throw  new RuntimeException("\n" +
                    "No user information found. "+id);
        }
        return  myUser;
   }

    @GetMapping("/username/{username}")
    public User getUser(@PathVariable String username) {
        User myUser = userService.findByUsername(username);
        if (myUser == null) {
            throw new RuntimeException("No user information found: " + username);
        }

        return myUser;
    }

   @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
      User myUser =  userService.findById(id);

       if(myUser==null){
           throw  new RuntimeException("\n" +
                   "No user information found "+id);
       }
       userService.deleteById(id);
       return "Delete user ID information "+id+" finished!!";
   }

   @PutMapping
   public User updateUser(@RequestBody User user){
       return  userService.save(user);
   }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        // ค้นหาผู้ใช้ด้วย id และทำการอัปเดต
        return userService.save(id, user); // ตัวอย่างการเรียก service สำหรับการอัปเดต
    }

}

