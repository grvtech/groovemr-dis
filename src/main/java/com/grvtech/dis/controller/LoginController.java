package com.grvtech.dis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView index() {

		// RestTemplate restTemplate = new RestTemplate();
		// User user = restTemplate.getForObject("http://localhost:8080/login",
		// User.class);

		ModelAndView modelAndView = new ModelAndView();
		// modelAndView.addObject("username", user.getUsername());
		// modelAndView.addObject("password", user.getPassword());
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ModelAndView login() {

		// RestTemplate restTemplate = new RestTemplate();
		// User user = restTemplate.getForObject("http://localhost:8080/login",
		// User.class);

		ModelAndView modelAndView = new ModelAndView();
		// modelAndView.addObject("username", user.getUsername());
		// modelAndView.addObject("password", user.getPassword());
		modelAndView.setViewName("user");
		return modelAndView;
	}

	@RequestMapping(value = { "/subscribe" }, method = RequestMethod.POST)
	public ModelAndView subscribe() {

		// RestTemplate restTemplate = new RestTemplate();
		// User user = restTemplate.getForObject("http://localhost:8080/login",
		// User.class);

		ModelAndView modelAndView = new ModelAndView();
		// modelAndView.addObject("username", user.getUsername());
		// modelAndView.addObject("password", user.getPassword());
		modelAndView.setViewName("index");
		return modelAndView;
	}

}
