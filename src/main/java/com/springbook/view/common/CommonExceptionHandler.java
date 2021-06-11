package com.springbook.view.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class CommonExceptionHandler {
	
	public ModelAndView handleArithmeticException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/arithmeticError.jsp");
		return mav;
	}
	
	public ModelAndView handleNullPointerException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/nullPointerError.jsp");
		return mav;
	}
	
	public ModelAndView handleException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/error.jsp");
		return mav;
	}
}
