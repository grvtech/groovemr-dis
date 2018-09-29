package com.grvtech.dis.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grvtech.dis.model.MessageResponse;
import com.grvtech.dis.model.User;
import com.grvtech.dis.repository.UserRepository;
import com.grvtech.dis.service.UserService;
import com.grvtech.dis.util.HttpUtil;

@RestController
public class LoginController {

	@Autowired
	UserRepository repository;
	@Autowired
	UserService userService;

	@RequestMapping(value = {"/", "/index.html", "/radu/gabor", "/radu/index.html"}, method = RequestMethod.GET)
	public ModelAndView index() {
		User user = repository.findById(2);
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
		modelAndView.addObject(user);
		return modelAndView;
	}

	@RequestMapping(value = {"/login/login"}, method = RequestMethod.POST)
	public MessageResponse login(final HttpServletRequest request) {

		// RestTemplate restTemplate = new RestTemplate();
		// User user = restTemplate.getForObject("http://localhost:8080/login",
		// User.class);

		JsonNode jn = HttpUtil.getJSONFromPost(request);
		System.out.println("-----------------------------------------");
		System.out.println("This is LOGIN POST API");
		System.out.println("-----------------------------------------");
		System.out.println("timestamp : " + jn.get("timestamp").toString());
		System.out.println("elements : " + jn.get("elements").toString());

		String state = jn.get("state").toString().replaceAll("\"", "");
		MessageResponse mresp = new MessageResponse();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddhhmmss");
		System.out.println("----------------------------------------- state:" + state);

		if (state.equals("enc")) {
			String elements = jn.get("elements").toString().replaceAll("\"", "");
			elements = new String(Base64.getDecoder().decode(elements));
			// System.out.println(new
			// String(Base64.getDecoder().decode(elements)));

			ObjectMapper mapper = new ObjectMapper();
			JsonFactory factory = mapper.getFactory();
			JsonParser parser;
			try {
				parser = factory.createParser(elements);
				JsonNode jnode = mapper.readTree(parser);

				System.out.println("-----------------------------------------");
				System.out.println("-username : " + jnode.get("username"));
				System.out.println("-password : " + jnode.get("password"));
				System.out.println("-----------------------------------------");

				String uname = jnode.get("username").toString().replaceAll("\"", "");
				String upass = jnode.get("password").toString().replaceAll("\"", "");

				User user = userService.getUserByUsernamePassword(uname, upass);

				if (user.isEmpty()) {
					mresp.setState("clear");
					mresp.setStatus("error");
					mresp.setMessage("this is error message");
					mresp.setTimestamp(sdf.format(new Date()));
				} else {
					mresp.setState("enc");
					mresp.setStatus("success");
					mresp.setMessage("this is  message");
					mresp.setTimestamp(sdf.format(new Date()));
					// mresp.setElements(Base64.getEncoder().encodeToString(elements.getBytes()));

					mresp.setElements(mapper.writeValueAsString(user));
				}
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			String elements = jn.get("elements").toString().replaceAll("\\\\", "").replaceAll("^\"(.*)\"$", "$1");

			System.out.println(elements.replaceAll("^\"(.*)\"$", "$1"));

			ObjectMapper mapper = new ObjectMapper();
			JsonFactory factory = mapper.getFactory();
			JsonParser parser;
			try {
				parser = factory.createParser(elements);
				JsonNode jnode = mapper.readTree(parser);

				System.out.println("-----------------------------------------");
				System.out.println("-username : " + jnode.get("username"));
				System.out.println("-password : " + jnode.get("password"));
				System.out.println("-----------------------------------------");

			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("-----------------------------------------");

		ModelAndView modelAndView = new ModelAndView();
		// modelAndView.addObject("username", user.getUsername());
		// modelAndView.addObject("password", user.getPassword());

		modelAndView.addObject(mresp);
		modelAndView.setViewName("index");
		return mresp;
	}

	@RequestMapping(value = {"/login/subscribe"}, method = RequestMethod.POST)
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

	@RequestMapping(value = {"/login/forgot"}, method = RequestMethod.POST)
	public ModelAndView forgot() {

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
