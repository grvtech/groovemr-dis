package com.grvtech.dis.configuration;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.grvtech.dis.service.administration.IOrganizationService;

@Configuration
public class DisConfig {

	@Autowired
	IOrganizationService orgservice;

	@Bean("license")
	public String getLicence() {
		String licence = "";
		try {
			licence = orgservice.getOrganizationLicence();
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException | UnsupportedEncodingException
				| IllegalBlockSizeException | BadPaddingException | JsonProcessingException e) {
			e.printStackTrace();
		}
		return licence;
	}
}
