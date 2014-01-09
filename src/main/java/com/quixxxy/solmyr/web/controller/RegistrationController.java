package com.quixxxy.solmyr.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.quixxxy.solmyr.domain.User;
import com.quixxxy.solmyr.service.UserService;
import com.quixxxy.solmyr.util.AvailabilityStatus;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView mav = new ModelAndView("solmyr.registration.view");
		mav.addObject(new User());
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult result, SessionStatus status,
			HttpSession session) {
		if (result.hasErrors()) {
			return "solmyr.registration.view";
		}
		userService.addUser(user);
		Authentication auth = userService.authenticate(user);
		SecurityContext ctx = SecurityContextHolder.getContext();
		ctx.setAuthentication(auth);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, ctx);
		status.setComplete();
		return "redirect:/";
	}
	
	@RequestMapping(value="/checkname", method=RequestMethod.GET)
	public @ResponseBody AvailabilityStatus getAvailability(@RequestParam String username) {
		User user = userService.getUserByName(username);
		if (user == null) {
			return AvailabilityStatus.available();
		}
		return AvailabilityStatus.notAvailable(username);
	}
}
