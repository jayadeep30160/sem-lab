package com.klu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	public User findByUsername(String un);

}
