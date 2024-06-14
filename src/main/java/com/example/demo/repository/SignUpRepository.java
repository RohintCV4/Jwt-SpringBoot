package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SignUp;

@Repository


public interface SignUpRepository extends JpaRepository<SignUp, Long> {
    
//    static SignUp findOneByEmailIdIgnoreCaseAndPassword(String email, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	SignUp findByUsernameAndPassword(String username, String password);

}
