package com.grvtech.dis.service.administration;

import java.util.UUID;

import com.grvtech.dis.model.administration.Organization;

public interface IOrganizationService {
	public Organization getOrganizationByUUID(UUID uuidorganization);
}
