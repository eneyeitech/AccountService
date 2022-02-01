package com.example.accountservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface Repository extends CrudRepository<User, Long> {
}
