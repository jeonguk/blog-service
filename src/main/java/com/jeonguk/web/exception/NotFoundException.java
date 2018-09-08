package com.jeonguk.web.exception;

public class NotFoundException extends RuntimeException{
	NotFoundException(){
		super();
	}
	public NotFoundException(String message){
		super(message);
	}
}
