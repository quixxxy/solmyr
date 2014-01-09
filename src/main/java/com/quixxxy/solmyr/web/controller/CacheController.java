package com.quixxxy.solmyr.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quixxxy.solmyr.cache.CacheService;

@Controller
@RequestMapping("/cache")
public class CacheController {

	@Autowired
	private CacheService service;
	
	@RequestMapping(value = "/{i}", method = RequestMethod.GET)
	public String execute(@PathVariable("i") int i) {
		service.someMethod(i);
		return "redirect:/";
	}
}
