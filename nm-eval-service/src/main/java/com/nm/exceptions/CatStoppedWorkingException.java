package com.nm.exceptions;

public class CatStoppedWorkingException extends RuntimeException {

	private static final long serialVersionUID = 2702399084058610704L;

	public CatStoppedWorkingException(Throwable biffer) {
		super(biffer);
	}
	
	public CatStoppedWorkingException(String message) {
		super(message);
	} 
}
