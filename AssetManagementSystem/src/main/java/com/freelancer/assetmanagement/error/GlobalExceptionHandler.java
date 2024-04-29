package com.freelancer.assetmanagement.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.freelancer.assetmanagement.util.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ResponseEntity<ResponseStructure<Object>> handleException(Exception e) {
		ResponseStructure<Object> response=new ResponseStructure<>();
		response.setData("Error");
		response.setMessage(e.getMessage());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ResponseStructure<Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

	}

}

