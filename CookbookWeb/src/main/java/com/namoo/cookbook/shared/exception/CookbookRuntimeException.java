package com.namoo.cookbook.shared.exception;

public class CookbookRuntimeException extends RuntimeException {
	//
	private static final long serialVersionUID = -3962402097929213147L;
	
	public CookbookRuntimeException(String msg) {
		//
		super(msg);
	}
	
	public CookbookRuntimeException(String msg, Throwable throwable) {
		//
		super(msg, throwable);
	}
}
