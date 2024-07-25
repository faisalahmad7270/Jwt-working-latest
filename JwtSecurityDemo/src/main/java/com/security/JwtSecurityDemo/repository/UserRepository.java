package com.security.JwtSecurityDemo.repository;

import java.util.Optional;

//import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.JwtSecurityDemo.entity.Role;
import com.security.JwtSecurityDemo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public Optional<User> findByEmail(String email);
	public User findByRole(Role role);
}
