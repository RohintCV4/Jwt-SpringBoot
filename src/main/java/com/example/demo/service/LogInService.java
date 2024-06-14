package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.SignUpRepository;

@Service
public class LogInService {
	@Autowired
	private SignUpRepository signUpRepository;
	
}
