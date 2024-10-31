package com.adison.crud1033.services;

import com.adison.crud1033.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User save(int id, User user);
    List<User> findAll();
    User findById(Integer id);

    User authenticateUser(String username, String password);

    void  deleteById(Integer id);
    

    User findByUsername(String username);

}
