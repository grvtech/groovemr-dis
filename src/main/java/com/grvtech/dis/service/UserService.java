package com.grvtech.dis.service;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grvtech.dis.model.MessageRequest;
import com.grvtech.dis.model.MessageResponse;
import com.grvtech.dis.model.User;
import com.grvtech.dis.repository.UserRepository;
import com.grvtech.dis.util.GRVRestClient;

@Service
public class UserService implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository repository;

	@Value("${core.server}")
	private String serverCore;

	@Value("${uuidorganization}")
	private String uuidorganization;

	@Value("${emptysession}")
	private String emptysession;

	@Autowired
	private GRVRestClient grvrc;

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public User getUserById(int userId) {
		User user = repository.findById(userId);
		if (user.isEmpty()) {
			// not in memory - go get it
			System.out.println("-----------------------------------------");
			System.out.println("The user is NOT in memory db go fetch it from server");
			System.out.println("-----------------------------------------");
			RestTemplate restTemplate = new RestTemplate();
			// user = restTemplate.getForObject("http://localhost:8080/user/gu"
			// + userId,
			// User.class);
			Map<String, String> vars = new HashMap<>();
			vars.put("userid", Integer.toString(userId));
			// this should be a MessageRequest object

			user = restTemplate.postForObject("http://localhost:8080/user/gu", null, User.class, vars);

			if (user.isEmpty()) {
				user = new User();
			}
		}
		return user;
	}

	@Override
	@Async("processExecutor")
	public void process() {
		logger.info("Received request to process in ProcessServiceImpl.process()");
		try {
			Thread.sleep(15 * 1000);
			logger.info("Processing complete");
		} catch (InterruptedException ie) {
			logger.error("Error in ProcessServiceImpl.process(): {}", ie.getMessage());
		}
	}

	@Override
	public User getUserByUsernamePassword(String username, String password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
		User user = repository.findByUP(username, password);

		if (user.isEmpty()) {
			ObjectMapper mapper = new ObjectMapper();
			// not in memory - go get it
			System.out.println("-----------------------------------------");
			System.out.println("The user is NOT in memory db go fetch it from server");
			System.out.println("The user " + username + "    the password : " + password);
			System.out.println("-----------------------------------------");
			/**/

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("username", username);
			map.put("password", password);

			MessageRequest mr;

			mr = new MessageRequest(uuidorganization, emptysession, "gubup", map);

			String url = "http://" + serverCore + "/user/gubup";
			MessageResponse mres = grvrc.postRequest(url, mr);
			mres = grvrc.clear(mres);
			if (mres.getStatus().equals("success")) {
				System.out.println("user object" + mapper.writeValueAsString(mres));
				// mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
				System.out.println(" ---- " + mres.getElements().get("user").asText());
				user = mapper.readValue(mres.getElements().get("user").asText(), User.class);
			}
			if (!user.isEmpty()) {
				addUser(user);
			}
		}
		return user;
	}

	@Override
	public User getUserByUsernamePin(String username, String pin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsernameImage(String username, String securityimage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(User user) {
		System.out.println("------------------------------------");
		int r = repository.insert(user);
		System.out.println("------------------------------------");
		// process();
		// addUserToCore(user);
		return (r == 1) ? true : false;
	}

	@Override
	@Async("processExecutor")
	public void addUserToCore(User user) {
		new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		new ObjectMapper();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", user.getUsername());
		map.put("email", user.getEmail());
		map.put("password", user.getPassword());
		map.put("uuiduser", user.getUuiduser().toString());
		map.put("uuidperson", user.getUuidperson().toString());
		map.put("pin", user.getPin());
		map.put("logo", user.getLogo());
		map.put("securityimage", user.getSecurityimage());
		map.put("authmethod", user.getAuthmethod());

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(int iduser) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserByEmailPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
