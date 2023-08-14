package com.jpacrud.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jpacrud.demo.model.SignUpModel;

@Repository
public interface SignUpRepository extends JpaRepository<SignUpModel, String>{
	
}