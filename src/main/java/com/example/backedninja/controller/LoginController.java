package com.example.backedninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.backedninja.constant.ViewConstant;

@Controller
public class LoginController {
	
	private static final Log Logger = LogFactory.getLog(LoginController.class);
	
	
	@GetMapping("/login")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout) {
		
		Logger.info("METHOD: showLoginForm() -- PARAMS: error=" + error + ", logout:" + logout);
		
		model.addAttribute("logout", logout);
		model.addAttribute("error", error);		
		Logger.info("Returning to login View");
		
		return ViewConstant.LOGIN;
	}
	
	@GetMapping({"/loginsuccess", "/"})
	public String loginCheck() {
		Logger.info("METHOD: loginCheck()");
		Logger.info("Returning contacts view");
		return "redirect:/contacts/showContact";	
	
	}

}
