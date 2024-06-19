package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SignUpDto;
import com.example.demo.entity.SignUp;
import com.example.demo.repository.SignUpRepository;
import com.example.demo.utils.ApiResponse;
import com.example.demo.utils.EncDecUtils;
import com.example.demo.utils.Role;

@Service
public class SignUpService {

	@Autowired
	private SignUpRepository signUpRepository;
	@Autowired
	private EncDecUtils encdecUtils;

	public ApiResponse SignIn(SignUpDto signUpDto) {
		ApiResponse apiResponse = new ApiResponse();
		SignUp signUp = new SignUp();
		signUp.setUsername(signUpDto.getUsername());
		signUp.setEmail(signUpDto.getEmail());
		signUp.setGender(signUpDto.getGender());

		try {
			signUp.setPassword(encdecUtils.encrypt(signUpDto.getPassword()));
		} catch (Exception e) {
			apiResponse.setError("Error encrypting password: " + e.getMessage());
			return apiResponse;
		}

		signUp.setPhNum(signUpDto.getPhNum());
		signUp.setRole(Role.user);

		signUpRepository.save(signUp);
		apiResponse.setData(signUp);
		return apiResponse;

	}
}
