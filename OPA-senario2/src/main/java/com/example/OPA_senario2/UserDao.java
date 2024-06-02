package com.example.OPA_senario2;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByEmail(String email);
}
