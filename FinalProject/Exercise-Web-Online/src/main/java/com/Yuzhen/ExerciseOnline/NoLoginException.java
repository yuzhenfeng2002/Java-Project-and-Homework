package com.Yuzhen.ExerciseOnline;

public class NoLoginException extends Exception{
	private static final long serialVersionUID = 1L;
	public NoLoginException() {
		super();
	}
	public NoLoginException(String message) {
		super(message);
	}
}