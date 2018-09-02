package com.grvtech.dis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.grvtech.dis.model.User;
import com.grvtech.dis.repository.UserRepository;

@RestController
public class LoginController {

	@Autowired
	UserRepository repository;

	@RequestMapping(value = { "/", "/index.html" }, method = RequestMethod.GET)
	public ModelAndView index() {
		User user = repository.findById(2);
		/*
		
		if (user.isEmpty()) {
			System.out.println("-----------------------------------------");
			System.out.println("The user 2 is NOT in memory db : NON");
			System.out.println("-----------------------------------------");

			
			
			
			
			//user = restTemplate.getForObject("http://localhost:8080/user/2", User.class);
			//repository.insert(user);

			System.out.println("-----------------------------------------");
			System.out.println("add user in memory db : yes");
			System.out.println("-----------------------------------------");
			System.out.println("-----------------------------------------");
			System.out.println("next refresh shoud be ok");
			System.out.println("-----------------------------------------");

		} else {
			System.out.println("-----------------------------------------");
			System.out.println("The user 2 is in memory db : YES");
			System.out.println("-----------------------------------------");
		}

		// RestTemplate restTemplate = new RestTemplate();
		// User user = restTemplate.getForObject("http://localhost:8080/user/2",
		// User.class);
	*/
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject(user);
		return modelAndView;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ModelAndView login(@RequestBody @Valid String username,@RequestBody @Valid String password,  BindingResult bindingResult, final HttpServletRequest request) {

		// RestTemplate restTemplate = new RestTemplate();
		// User user = restTemplate.getForObject("http://localhost:8080/login",
		// User.class);

		System.out.println("-----------------------------------------");
		System.out.println("username : "+request.getParameter("username"));
		System.out.println("password : "+request.getParameter("password"));
		System.out.println("-----------------------------------------");
		
		
		
		ModelAndView modelAndView = new ModelAndView();
		// modelAndView.addObject("username", user.getUsername());
		// modelAndView.addObject("password", user.getPassword());
		
		User user = repository.findById(2);
		
		modelAndView.addObject(user);
		modelAndView.setViewName("index");
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
