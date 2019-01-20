package com.grvtech.dis.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grvtech.dis.model.PatientResultItem;
import com.grvtech.dis.repository.UserRepository;
import com.grvtech.dis.service.UserService;

@RestController
public class SearchController {

	@Autowired
	UserRepository repository;
	@Autowired
	UserService userService;

	@RequestMapping(value = {"/search/patient"}, method = RequestMethod.GET)
	public List<PatientResultItem> index() {
		List<PatientResultItem> result = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);
		// mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		try {
			ClassPathResource res = new ClassPathResource("");
			System.out.println("--------------------------------");
			System.out.println("classpath : " + res.getFile().getAbsolutePath());
			ClassPathResource resource = new ClassPathResource("/templates/json/search.json");
			InputStream input = resource.getInputStream();
			result = mapper.readValue(input, mapper.getTypeFactory().constructCollectionType(List.class, PatientResultItem.class));
			// Plain
			// JSON
			// mapper.writerWithDefaultPrettyPrinter().writeValue(new
			// File("result.json"), carFleet);//Prettified JSON
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
