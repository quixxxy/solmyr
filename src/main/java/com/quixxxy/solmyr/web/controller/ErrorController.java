package com.quixxxy.solmyr.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@RequestMapping(value = "/404")
	public String error404() {
		return "solmyr.error.404";
	}

	@RequestMapping(value = "/500")
	public String error505() {
		return "solmyr.error.500";
	}
}
