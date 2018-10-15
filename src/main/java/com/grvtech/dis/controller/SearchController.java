package com.grvtech.dis.controller;

import java.io.InputStream;

import org.cryptacular.io.ClassPathResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grvtech.dis.model.PatientSearchResult;
import com.grvtech.dis.repository.UserRepository;
import com.grvtech.dis.service.UserService;

@RestController
public class SearchController {

	@Autowired
	UserRepository repository;
	@Autowired
	UserService userService;

	@RequestMapping(value = {"/search/patient"}, method = RequestMethod.GET)
	public PatientSearchResult index() {
		PatientSearchResult result = new PatientSearchResult();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			ClassPathResource resource = new ClassPathResource("search.json");
			InputStream input = resource.getInputStream();
			result = mapper.readValue(input, PatientSearchResult.class);// Plain
																		// JSON
			// mapper.writerWithDefaultPrettyPrinter().writeValue(new
			// File("result.json"), carFleet);//Prettified JSON
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
