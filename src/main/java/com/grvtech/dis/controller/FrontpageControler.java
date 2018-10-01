package com.grvtech.dis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.grvtech.dis.repository.UserRepository;

@RestController
public class FrontpageControler {

	@Autowired
	UserRepository repository;
	@Autowired
	//FrontpageService frontpageService;

	@RequestMapping(value = {"/frontpage/get"}, method = RequestMethod.GET)
	public ModelAndView index() {

		/*
		 * 
		 * if (user.isEmpty()) {
		 * System.out.println("-----------------------------------------");
		 * System.out.println("The user 2 is NOT in memory db : NON");
		 * System.out.println("-----------------------------------------");
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //user = restTemplate.getForObject("http://localhost:8080/user/2",
		 * User.class); //repository.insert(user);
		 * 
		 * System.out.println("-----------------------------------------");
		 * System.out.println("add user in memory db : yes");
		 * System.out.println("-----------------------------------------");
		 * System.out.println("-----------------------------------------");
		 * System.out.println("next refresh shoud be ok");
		 * System.out.println("-----------------------------------------");
		 * 
		 * } else {
		 * System.out.println("-----------------------------------------");
		 * System.out.println("The user 2 is in memory db : YES");
		 * System.out.println("-----------------------------------------"); }
		 * 
		 * // RestTemplate restTemplate = new RestTemplate(); // User user =
		 * restTemplate.getForObject("http://localhost:8080/user/2", //
		 * User.class);
		 */

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		
		return modelAndView;
	}
}
