package com.grvtech.dis.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = {"/", "/index.html", "/index.grvemr", "/dis/index.html"}, method = RequestMethod.GET)
	public ModelAndView index() {

		/*
		 * User user = repository.findById(2); if (user.isEmpty()) {
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
		// modelAndView.addObject(user);
		return modelAndView;
	}

	/*
	 * login with username and password
	 */
	@RequestMapping(value = {"/login/lup.grvemr"}, method = RequestMethod.POST)
	public ClientMessageResponse login(final HttpServletRequest request) {

		ClientMessageResponse mresp = new ClientMessageResponse();
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> map = new HashMap<>();
		JsonNode jn = HttpUtil.getJSONFromPost(request);
		try {
			ClientMessageRequest mr = new ClientMessageRequest(jn);
			String username = mr.getElements().get("username").asText();
			String password = mr.getElements().get("password").asText();

			User user = userService.getUserByUsernamePassword(username, password);

			if (user.isEmpty()) {
				map.put("error", "error-login");
				mresp = new ClientMessageResponse(false, mr, map);
			} else {
				Session sess = new Session(user.getUuiduser());
				map.put("uuidsession", sess.getUuidsession().toString());
				map.put("uuiduser", user.getUuiduser().toString());
				mresp = new ClientMessageResponse(true, mr, map);
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException
				| BadPaddingException | IOException | ParseException e1) {
			ObjectNode ob = mapper.createObjectNode();
			ob.put("error", "error-error");
			mresp = new ClientMessageResponse("error", "clear", "lup", ob);
			e1.printStackTrace();
		}

		return mresp;
	}

	@RequestMapping(value = {"/login/subscribe"}, method = RequestMethod.POST)
	public ClientMessageResponse subscribe(final HttpServletRequest request) {
		ClientMessageResponse cmr = new ClientMessageResponse();
		ObjectMapper mapper = new ObjectMapper();
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

		} catch (InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
				| NoSuchPaddingException | IOException | ParseException e) {
			ObjectNode ob = mapper.createObjectNode();
			ob.put("error", "error-error");
			cmr = new ClientMessageResponse("error", "clear", "lup", ob);

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
