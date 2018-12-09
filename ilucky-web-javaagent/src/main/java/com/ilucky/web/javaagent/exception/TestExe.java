package com.ilucky.web.javaagent.exception;

import javax.servlet.http.HttpServletResponse;

import com.ilucky.web.javaagent.controller.CommonService;

public class TestExe {

	public static void testExec(HttpServletResponse response) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int i = 0;
		System.out.println(10/i);
		CommonService.commonService(response, "exception");
	}
	
	public static void testThrows(HttpServletResponse response) throws MyException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		throw new MyException("IL");
	}
}
