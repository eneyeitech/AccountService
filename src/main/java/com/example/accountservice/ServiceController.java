package com.example.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ServiceController {

    private Store store;

    @Autowired
    public ServiceController(Store store) {
        this.store = store;
    }

    @PutMapping("api/admin/user/role")
    public String changeUserRole() {
        return "changeUserRole";
    }

    @DeleteMapping("api/admin/user")
    public String deleteUser(@RequestParam String email) {
        User deletedUser = store.getUserStore().remove(email);
        if (deletedUser instanceof User) {
            return String.format("User %s:%s deleted", deletedUser.getEmail(), deletedUser.getName());
        } else {
            return "User doesn't exist";
        }
    }

    @GetMapping("api/admin/user")
    public Map<String, User> getUsers() {
        return store.getUserStore();
    }
}
