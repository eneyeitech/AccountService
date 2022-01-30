package com.example.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ServiceController {

    private Store store;
    @Autowired
    UserRepository userRepo;

    @Autowired
    public ServiceController(Store store) {
        this.store = store;
    }

    @PutMapping("api/admin/user/role")
    public String changeUserRole() {
        return "changeUserRole";
    }

    @DeleteMapping("api/admin/user")
    public String deleteUser() {
        return "deleteUser";
    }

    @GetMapping("api/admin/user")
    public Object getUsers() {
        //return store.getUserStore();
        return userRepo.getUsers();
    }
}
