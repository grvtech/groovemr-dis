package com.grvtech.dis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.grvtech.dis.model.User;
import com.grvtech.dis.repository.UserRepository;

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
		User user = repository.findById(userId);k
		if (user.isEmpty()) {
			// not in memory - go get it
			System.out.println("-----------------------------------------");
			System.out.println("The user is NOT in memory db go fetch it from server");
			System.out.println("-----------------------------------------");
			RestTemplate restTemplate = new RestTemplate();
			//user = restTemplate.getForObject("http://localhost:8080/user/gu" + userId, User.class);
			
			user = restTemplate.postForObject("http://localhost:8080/user/gu", , User.class);
			
			if (user.isEmpty()) {
				user = new User();
			}
		}
		return user;
	}

	@Override
	public User getUserByUsernamePassword(String username, String password) {
		User user = repository.findByUP(username, password);
		if (user.isEmpty()) {
			// not in memory - go get it
			System.out.println("-----------------------------------------");
			System.out.println("The user is NOT in memory db go fetch it from server");
			System.out.println("-----------------------------------------");
			RestTemplate restTemplate = new RestTemplate();
			user = restTemplate.getForObject("http://localhost:8080/user/gu" + userId, User.class);
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
