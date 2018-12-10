package com.grvtech.dis.configuration;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.grvtech.dis.model.administration.Organization;
import com.grvtech.dis.service.administration.IOrganizationService;

@Configuration
public class DisConfig {

	@Value("${uuidorganization}")
	private String orgid;

	@Autowired
	IOrganizationService orgservice;

	@Bean("license")
	public String getLicence() {
		Organization organization = orgservice.getOrganizationByUUID(UUID.fromString(orgid));
		return organization.getLicence();
	}
}
