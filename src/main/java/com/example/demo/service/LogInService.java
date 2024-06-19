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
import com.example.demo.utils.EncDecUtils;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.Role;

@Service
public class LogInService {
	@Autowired
	private SignUpRepository signUpRepository;
	@Autowired
	private EncDecUtils encdecUtils;

	@Autowired
	private JwtUtils jwtUtils;

	public ApiResponse LogIn(LogInDto logInDto) {
		ApiResponse apiResponse = new ApiResponse();

		SignUp login = signUpRepository.findByUsername(logInDto.getUsername());

		if (login == null || login.getId() == null) {
			apiResponse.setData("Can't Login");
			return apiResponse;

		}

		try {
			String decryptedPassword = encdecUtils.decrypt(login.getPassword());
			if (!decryptedPassword.equals(logInDto.getPassword())) {
				apiResponse.setData("Invalid credentials");
				return apiResponse;
			}
		} catch (Exception e) {
			apiResponse.setData("Error decrypting password: " + e.getMessage());
			return apiResponse;
		}

		if (login.getRole().equals(Role.admin)) {
			List<SignUp> entireData = signUpRepository.findAll();
			apiResponse.setData1(entireData);
		}
		
//		if(jwtUtils.takeData()) {
//			
//		}

		String token = jwtUtils.generateJwt(login);

		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);

		apiResponse.setData(data);

		return apiResponse;
	}

}
