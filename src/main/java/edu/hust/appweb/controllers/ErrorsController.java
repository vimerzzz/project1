package edu.hust.appweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hust.appweb.domain.ErrorCode;
import edu.hust.appweb.repository.ErrorCodeRepository;

@Controller
public class ErrorsController implements ErrorController{
	private int code;
	
	@Autowired
	ErrorCodeRepository errorCodeRepository;

    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }
	
	@RequestMapping("/error")
	public String duplicateError() {
		ErrorCode errorCode = errorCodeRepository.findErrorById(1);
		if(errorCode == null) {
			return "error";
		}
		code = errorCode.getErrorcode();
		switch (code) {
			case 1: return "duplicate-classroom";
			case 2: return "not-found-student";
			case 3: return "not-found-teacher";
			default: return "error";
		}
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
}
