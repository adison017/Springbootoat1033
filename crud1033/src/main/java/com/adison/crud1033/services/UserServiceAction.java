package com.adison.crud1033.services;

import com.adison.crud1033.entity.User;
import com.adison.crud1033.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class UserServiceAction implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceAction(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    private static final String SECRET_KEY = "mySuperSecretKey";



    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // เข้ารหัสรหัสผ่าน
        return userRepository.save(user);
    }

    @Override
    public User save(int id, User user) {
        User existingUser = findById(id); // ค้นหาผู้ใช้ที่มี ID
        // อัปเดตข้อมูลผู้ใช้
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword())); // เข้ารหัสรหัสผ่าน
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser); // บัน
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> result = userRepository.findById(id);
        User data = null;
        if (result.isPresent()) {
            data = result.get();
        } else {
            throw new RuntimeException("ไม่พบข้อมูลผู้ใช้  " + id);
        }
        return data;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> result = userRepository.findByUsername(username);
        User data;
        if (result.isPresent()) {
            data = result.get();


        } else {
            throw new RuntimeException("ไม่พบข้อมูลผู้ใช้ " + username);
        }
        return data;
    }

    @Override
    public User authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.orElse(null);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}


