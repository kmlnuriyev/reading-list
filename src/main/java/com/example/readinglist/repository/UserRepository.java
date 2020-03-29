package com.example.readinglist.repository;

import com.example.readinglist.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a bean called userRepository
 * CRUD refers Create, Read, Update, Delete
 */

public interface UserRepository extends CrudRepository<User, Integer> {
}
