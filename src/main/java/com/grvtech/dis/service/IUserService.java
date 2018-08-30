package com.grvtech.dis.service;

import java.util.List;

import com.grvtech.dis.model.User;

public interface IUserService {
	List<User> getAllUsers();

	User getUserById(int userId);

	User getUserByUsernamePassword(String username, String password);

	User getUserByUsernamePin(String username, String pin);

	User getUserByUsernameImage(String username, String securityimage);

	boolean addUser(User user);

	void updateUser(User user);

	void deleteUser(int iduser);
}
