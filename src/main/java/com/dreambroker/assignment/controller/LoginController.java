package com.dreambroker.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

	@GetMapping("/login")
	public ModelAndView login(
			@RequestParam(name="accountcreated", defaultValue="false") boolean accountCreated) {

		ModelAndView mav =  new ModelAndView("login");
		mav.addObject("accountCreated", accountCreated);
		return mav;
	}
}
