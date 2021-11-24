package com.example.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)  
public class JobNotFoundException extends RuntimeException {
public JobNotFoundException(String message)
{
super(message);	
}
}
