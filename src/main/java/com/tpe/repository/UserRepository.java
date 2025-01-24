package com.tpe.repository;

import com.tpe.domain.User;
import com.tpe.exception.ResourceNotFoundException;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUserName(String username) throws ResourceNotFoundException;
    Boolean existsByUserName(String username);
}
