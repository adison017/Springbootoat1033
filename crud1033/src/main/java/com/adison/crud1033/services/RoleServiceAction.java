package com.adison.crud1033.services;

import com.adison.crud1033.entity.Role;
import com.adison.crud1033.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceAction implements RoleService {
    private RoleRepository roleRepository;
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role save(int role_id, Role role) {
        Role existingRole = findById(role_id); // ค้นหาผู้ใช้ที่มี ID
        // อัปเดตข้อมูลผู้ใช้
        existingRole.setRole_name(role.getRole_name());


        return roleRepository.save(existingRole); // บัน
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Integer role_id) {
        Optional<Role> result = roleRepository.findById(role_id);
        Role data=null;
        if(result.isPresent()){
            data=result.get();
        }else {
            throw  new RuntimeException("ไม่พบข้อมูลผู้ใช้  "+role_id);
        }
        return data;
    }

    @Override
    public void deleteById(Integer role_id) {
        roleRepository.deleteById(role_id);
    }

    public RoleServiceAction(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
