package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.LogInDto;
import com.example.demo.entity.SignUp;
import com.example.demo.repository.SignUpRepository;
import com.example.demo.utils.JwtUtils;

@Service
public class LogInService {
	@Autowired
	private SignUpRepository signUpRepository;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public ApiResponse LogIn(LogInDto logInDto) {
		ApiResponse apiResponse=new ApiResponse();
		
		SignUp login=signUpRepository.findByUsernameAndPassword(logInDto.getUsername(),logInDto.getPassword());
		
		if(login.getId() == null) {
			apiResponse.setData("Can't Login");
			
		}
		String token = jwtUtils.generateJwt(login);

        Map<String , Object> data = new HashMap<>();
        data.put("accessToken", token);

        apiResponse.setData(data);

        return apiResponse;
	}
	
}
