package com.gmail.arthurstrokov.ormedia.repository;

import com.gmail.arthurstrokov.ormedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}