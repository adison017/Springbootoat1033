package com.adison.crud1033.services;

import com.adison.crud1033.entity.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);

    Role save(int role_id, Role role);

    List<Role> findAll();

    Role findById(Integer role_id);

    void deleteById(Integer role_id);
}
