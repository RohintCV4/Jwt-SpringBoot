package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SignUpDto;
import com.example.demo.entity.SignUp;
import com.example.demo.repository.SignUpRepository;
import com.example.demo.utils.ApiResponse;
import com.example.demo.utils.UserRole;

@Service
public class SignUpService {

	@Autowired
	private SignUpRepository signUpRepository;

	public ApiResponse SignIn(SignUpDto signUpDto) {
		ApiResponse apiResponse = new ApiResponse();
		SignUp signUp = new SignUp();
		signUp.setUsername(signUpDto.getUsername());
		signUp.setEmail(signUpDto.getEmail());
		signUp.setGender(signUpDto.getGender());
		signUp.setPassword(signUpDto.getPassword());
		signUp.setPhNum(signUpDto.getPhNum());
		signUp.setRole(UserRole.user);

		signUpRepository.save(signUp);
		apiResponse.setData(signUp);
		return apiResponse;

	}
}
