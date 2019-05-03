package com.authenticate.loginservice.Repositories;

import com.authenticate.loginservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer> {
     @Query(
             value = "SELECT * FROM User u WHERE u.email = ?1",
             nativeQuery = true)
     User findByEmail(String email);
}
