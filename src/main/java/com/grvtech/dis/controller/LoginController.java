package com.grvtech.dis.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grvtech.dis.model.ClientMessageRequest;
import com.grvtech.dis.model.ClientMessageResponse;
import com.grvtech.dis.model.Session;
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
	public ClientMessageResponse login(final HttpServletRequest request) {

		// RestTemplate restTemplate = new RestTemplate();
		// User user = restTemplate.getForObject("http://localhost:8080/login",
		// User.class);
		ClientMessageResponse mresp = new ClientMessageResponse();
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddhhmmss");
		JsonNode jn = HttpUtil.getJSONFromPost(request);

		System.out.println("-----------------------------------------");
		System.out.println("This is LOGIN POST API");
		System.out.println("-----------------------------------------");
		System.out.println("timestamp : " + jn.get("timestamp").toString());
		System.out.println("elements : " + jn.get("elements").toString());

		// jn.get("state").toString().replaceAll("\"", "");

		try {
			ClientMessageRequest mr = new ClientMessageRequest(jn);

			String username = mr.getElements().get("username").asText();
			String password = mr.getElements().get("password").asText();

			System.out.println("-----------------------------------------");
			System.out.println("This is LOGIN POST API");
			System.out.println("-----------------------------------------");
			System.out.println("username : " + username);
			System.out.println("password : " + password);

			User user = userService.getUserByUsernamePassword(username, password);

			if (user.isEmpty()) {
				mresp.setState("enc");
				mresp.setStatus("error");
				mresp.setMessage("Wrong username or password");
				mresp.setTimestamp(sdf.format(new Date()));
				HashMap<String, String> error1 = new HashMap<String, String>();
				error1.put("username", "i18n:wrongusername_txt");
				error1.put("password", "i18n:wrongpassword_txt");
				String base64 = Base64.getEncoder().encodeToString(mapper.writeValueAsBytes(error1));
				ObjectNode obj = mapper.createObjectNode();
				obj.put("error", base64);
				mresp.setElements(obj);
			} else {
				Session sess = new Session(user.getUuiduser());
				mresp.setState("enc");
				mresp.setStatus("success");
				mresp.setMessage("this is  message");
				mresp.setTimestamp(sdf.format(new Date()));
				ObjectNode obj = mapper.createObjectNode();
				String base64User = Base64.getEncoder().encodeToString(mapper.writeValueAsBytes(user));
				obj.put("user", base64User);
				String base64Session = Base64.getEncoder().encodeToString(mapper.writeValueAsBytes(sess));
				obj.put("session", base64Session);
				mresp.setElements(obj);
			}
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return mresp;
	}

	@RequestMapping(value = {"/login/subscribe"}, method = RequestMethod.POST)
	public ClientMessageResponse subscribe(final HttpServletRequest request) {
		ClientMessageResponse cmr = new ClientMessageResponse();
		new ObjectMapper();
		new SimpleDateFormat("yyyMMddhhmmss");
		JsonNode jn = HttpUtil.getJSONFromPost(request);
		try {
			ClientMessageRequest mr = new ClientMessageRequest(jn);
			mr.getElements().get("fname").asText();
			mr.getElements().get("lname").asText();
			String email = mr.getElements().get("email").asText();
			String username = mr.getElements().get("username").asText();
			String password = mr.getElements().get("password").asText();

			User user = userService.getUserByEmailPassword(email, password);
			if (user.isEmpty()) {
				user = userService.getUserByUsernamePassword(username, password);
				if (user.isEmpty()) {
					// good create user
					user = new User(0, UUID.randomUUID(), UUID.randomUUID(), username, password, email, "1234", "aaa", "aaa", "aaaa");
					if (userService.addUser(user)) {
						userService.process();
						userService.addUserToCore(user);
					}

				} else {
					// add username error
				}
			} else {
				// add email error
			}

			// TODO verify if is not a person as well

			/*
			 * validations for subscribe : 1. verify username is not already
			 * entered 2. verify email is not already entered
			 */

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cmr;
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
