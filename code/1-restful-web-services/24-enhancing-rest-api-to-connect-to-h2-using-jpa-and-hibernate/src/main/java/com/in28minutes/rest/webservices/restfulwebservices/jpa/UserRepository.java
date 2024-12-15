package com.in28minutes.rest.webservices.restfulwebservices.jpa;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 24-enhancing-rest-api-to-connect-to-h2-using-jpa-and-hibernate

public interface UserRepository extends JpaRepository<User, Integer> {
}