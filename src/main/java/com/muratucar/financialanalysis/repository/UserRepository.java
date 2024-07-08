package com.muratucar.financialanalysis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muratucar.financialanalysis.entity.User;

public interface UserRepository extends JpaRepository<User , Long>{

	Optional<User> findByUsername(String username);
	
}