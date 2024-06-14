package com.example.demo.common;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResponse {

	private Integer status;
    private Object data;
    private Object error;
    
    public ApiResponse() {
    	this.status=HttpStatus.OK.value();
    	this.data=data;
    	this.error=error;
    }
}
