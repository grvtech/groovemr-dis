package com.grvtech.dis.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.grvtech.dis.model.User;

public interface IUserService {
	List<User> getAllUsers();

	User getUserById(int userId);

	User getUserByUsernamePassword(String username, String password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, JsonProcessingException, IOException;

	User getUserByUsernamePin(String username, String pin);

	User getUserByUsernameImage(String username, String securityimage);

	boolean addUser(User user);

	void updateUser(User user);

	void deleteUser(int iduser);

	void process();

	User getUserByEmailPassword(String email, String password);

	void addUserToCore(User user);
}
