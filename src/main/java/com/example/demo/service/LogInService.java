package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LogInDto;
import com.example.demo.entity.SignUp;
import com.example.demo.repository.SignUpRepository;
import com.example.demo.utils.ApiResponse;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.UserRole;

@Service
public class LogInService {
	@Autowired
	private SignUpRepository signUpRepository;

	@Autowired
	private JwtUtils jwtUtils;

	public ApiResponse LogIn(LogInDto logInDto) {
		ApiResponse apiResponse = new ApiResponse();

		SignUp login = signUpRepository.findByUsernameAndPassword(logInDto.getUsername(), logInDto.getPassword());

		if (login.getId() == null) {
			apiResponse.setData("Can't Login");

		}
		
		if(login.getRole().equals(UserRole.admin)) {
			List<SignUp> entireData=signUpRepository.findAll();
			apiResponse.setData1
			(entireData);
		}
		
		String token = jwtUtils.generateJwt(login);

		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);

		apiResponse.setData(data);

		return apiResponse;
	}

}
