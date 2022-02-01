package com.example.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepository{
    final private Map<String, User> users = new ConcurrentHashMap<>();

    @Autowired
    Repository repository;


    public User findUserByEmail(String email) {
        List<User> list = new ArrayList<>();
        repository.findAll().forEach(user -> list.add(user));

        Optional<User> matchingUser = list.stream().
                filter(u -> u.getEmail().equalsIgnoreCase(email)).
                findFirst();
        User user = null;
        if (matchingUser.isPresent()){
            user = matchingUser.get();
            user.setEmail(email);
        }
        //return matchingUser.orElse(null);
        return user;
    }

    public User findUserByID(long id) {
        return repository.findById(id).get();
    }

    public void save(User user) {
        //users.put(user.getEmail(), user);
        User savedUser = repository.save(user);
        System.out.println(savedUser + " saved.");
    }

    public void updatePassword(User user) {
        //users.put(user.getEmail(), user);
        User savedUser = repository.save(user);
        System.out.println(savedUser + " saved.");
    }

    public boolean hasUser(User user) {
        //return users.containsKey(user.getEmail());
        List<User> list = new ArrayList<>();
        repository.findAll().forEach(us -> list.add(us));

        Optional<User> matchingUser = list.stream().
                filter(u -> u.getEmail().equalsIgnoreCase(user.getEmail())).
                findFirst();

        return matchingUser.isPresent();
    }

    public boolean hasUser(String email) {
        //return users.containsKey(user.getEmail());
        List<User> list = new ArrayList<>();
        repository.findAll().forEach(us -> list.add(us));

        Optional<User> matchingUser = list.stream().
                filter(u -> u.getEmail().equalsIgnoreCase(email)).
                findFirst();

        return matchingUser.isPresent();
    }

    public Object getUsers() {
        //return  users;
        List<User> us = new ArrayList<>();
        repository.findAll().forEach(user -> us.add(user));
        return us;
    }


}
