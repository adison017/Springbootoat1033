package com.adison.crud1033.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Set;


@Entity
@Table(name="tbusers")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String username;
    private String email;
    private String password;
    private  String firstName;
    private  String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Transient // เพื่อแจ้งว่าไม่ต้องการเก็บฟิลด์เหล่านี้ในฐานข้อมูล
    private String message;

    @Transient
    private String responseRole;
    public enum Role {
        user, admin;

    }

    public User() {
    }

    public Role getRole() {
        return role;
    }

    public String getResponseRole() {
        return responseRole;
    }

    public void setResponseRole(String responseRole) {
        this.responseRole = responseRole;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getId() {
        return user_id;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public User(String username, String email, String password, String firstName, String lastName, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

}
