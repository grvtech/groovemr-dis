package com.grvtech.dis.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grvtech.dis.model.MessageRequest;
import com.grvtech.dis.model.User;
import com.grvtech.dis.repository.UserRepository;

@Service
public class UserService implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository repository;

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
	public User getUserByUsernamePassword(String username, String password) {
		User user = repository.findByUP(username, password);
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		// new SimpleDateFormat("yyyMMddhhmmss");

		if (user.isEmpty()) {
			// not in memory - go get it
			System.out.println("-----------------------------------------");
			System.out.println("The user is NOT in memory db go fetch it from server");
			System.out.println("-----------------------------------------");
			/**/
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.add("ApplicationID", "1234");

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("username", username);
			map.put("password", password);

			MessageRequest mr;
			try {
				mr = new MessageRequest(new Date(), "action", map);
				new HttpEntity<MessageRequest>(mr, headers);
				ResponseEntity<User> response;

				response = restTemplate.postForEntity("http://localhost:8080/user/gu", mapper.writeValueAsString(mr), User.class);
				user = response.getBody();

				if (user.isEmpty()) {
					user = new User();
				}

			} catch (InvalidKeyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidKeySpecException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidAlgorithmParameterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalBlockSizeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BadPaddingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(int iduser) {
		// TODO Auto-generated method stub

	}

}
