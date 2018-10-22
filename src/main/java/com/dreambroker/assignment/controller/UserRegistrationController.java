package com.dreambroker.assignment.controller;


import com.dreambroker.assignment.model.RegistrationUser;
import com.dreambroker.assignment.service.UserNameTakenException;
import com.dreambroker.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public ModelAndView showRegistrationPage() {

		ModelAndView mav =  new ModelAndView("registration");
		mav.addObject("user", new RegistrationUser());
		return mav;
	}

	@PostMapping("/register")
	public ModelAndView handleRegistration(@Valid @ModelAttribute("user") RegistrationUser user, BindingResult
			bindingResult) {

		if (bindingResult.hasErrors()) {
			ModelAndView mav =  new ModelAndView("registration", bindingResult.getModel());
			mav.addObject("user", user);
			return mav;
		}

		try {
			userService.createUser(user);
		} catch (IllegalArgumentException|UserNameTakenException ex) {
			ModelAndView mav = new ModelAndView("registration");
			mav.addObject("user", user);
			mav.addObject("errorMessage", ex.getMessage());
			return mav;
		}

		ModelAndView mav =  new ModelAndView(new RedirectView("/login?accountcreated=true", false, false, true));
		return mav;
	}
}
