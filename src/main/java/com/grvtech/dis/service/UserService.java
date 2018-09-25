package com.grvtech.dis.service;

import java.io.UnsupportedEncodingException;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grvtech.dis.model.User;
import com.grvtech.dis.repository.UserRepository;
import com.grvtech.dis.util.CryptoUtil;

@Service
public class UserService implements IUserService {

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
	public User getUserByUsernamePassword(String username, String password) {
		User user = repository.findByUP(username, password);
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		if (user.isEmpty()) {
			// not in memory - go get it
			System.out.println("-----------------------------------------");
			System.out.println("The user is NOT in memory db go fetch it from server");
			System.out.println("-----------------------------------------");
			/**/
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			try {
				map.add("username", CryptoUtil.encrypt("test", username));
				map.add("password", CryptoUtil.encrypt("test", password));
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {

				e1.printStackTrace();
			} catch (InvalidKeySpecException e1) {

				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {

				e1.printStackTrace();
			} catch (InvalidAlgorithmParameterException e1) {

				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {

				e1.printStackTrace();
			} catch (IllegalBlockSizeException e1) {
				e1.printStackTrace();
			} catch (BadPaddingException e1) {
				e1.printStackTrace();
			}

			new HttpEntity<MultiValueMap<String, Object>>(map, headers);
			ResponseEntity<User> response;
			try {
				response = restTemplate.postForEntity("http://localhost:8080/user/gu", mapper.writeValueAsString(map), User.class);
				user = response.getBody();
			} catch (RestClientException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			if (user.isEmpty()) {
				user = new User();
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
