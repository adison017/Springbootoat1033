package com.adison.crud1033.controllers;

import com.adison.crud1033.entity.Role;
import com.adison.crud1033.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping("/Role")
    public Role addRole(@RequestBody Role role){
        return  roleService.save(role);
    }

    @GetMapping("/Role")
    public List<Role> getAllrole(){
        return  roleService.findAll();
    }

    @GetMapping("/Role/{id}")
    public Role getRole(@PathVariable int id){
        Role myRole = roleService.findById(id);
        if (myRole==null) {
            throw  new RuntimeException("\n" +
                    "No role information found. "+id);
        }
        return  myRole;
    }

    @DeleteMapping("/Role/{id}")
    public String deleteRole(@PathVariable int id){
        Role myRole =  roleService.findById(id);

        if(myRole==null){
            throw  new RuntimeException("\n" +
                    "No role information found "+id);
        }
        roleService.deleteById(id);
        return "Delete role ID information "+id+" finished!!";
    }

    @PutMapping("/Role")
    public Role updateRole(@RequestBody Role role){
        return  roleService.save(role);
    }
    @PutMapping("/Role/{id}")
    public Role updateRole(@PathVariable int id, @RequestBody Role role) {
        // ค้นหาผู้ใช้ด้วย id และทำการอัปเดต
        return roleService.save(id, role); // ตัวอย่างการเรียก service สำหรับการอัปเดต
    }
}
