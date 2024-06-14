package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.LogInDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.service.LogInService;
import com.example.demo.service.SignUpService;

@RestController
@RequestMapping("/req")
public class SignUpController {
	
	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	private LogInService logInService;
	
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpDto signUpDto){
		ApiResponse apiResponse=signUpService.SignIn(signUpDto);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> logIn(@RequestBody LogInDto logInDto) {
//		System.out.println(logInDto.getUsername());
//		return null;
		ApiResponse apiResponse=logInService.LogIn(logInDto);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
 
		
	}
	
	
	
}
