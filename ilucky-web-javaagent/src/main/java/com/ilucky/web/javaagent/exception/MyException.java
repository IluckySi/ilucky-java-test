package com.ilucky.web.javaagent.exception;

/**
 * 自定义异常
 * @author IluckySi
 *
 */
public class MyException extends Exception {

	private static final long serialVersionUID = 1L;
	  
	public MyException(String message) {
	        super(message);
	   }
	
	public MyException(String message, Exception e) {
        super(message, e);
   }
}
