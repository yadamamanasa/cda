package org.jsp.cda.repository;

import java.util.Optional;

import org.jsp.cda.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository  extends JpaRepository<User, Integer>{

	Optional<User> findByUsernameAndPassword(String username, String password);
    @Query("select u from User u where u.email=:email ")
	Optional<User> findUserByEmail(String email);

}
