package com.sira.employe_monitoring_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sira.employe_monitoring_system.entity.Organistion;
import com.sira.employe_monitoring_system.exception.MandatoryFeildExcetion;
import com.sira.employe_monitoring_system.exception.OrganizationNameAlreadyExistsException;
import com.sira.employe_monitoring_system.repository.OrganizationRepository;

@Service
public class OrganizationserviceImplimentation implements Organizationservice {

	@Autowired
	private OrganizationRepository repo;

	@Override
	public Organistion regiterOrganization(Organistion org) {
		if ((org == null || (org.getContact() == null) || org.getCountry() == null || org.getEmail() == null
				|| org.getLocationOfOrganization() == null || org.getNameOfOrganization() == null
				|| org.getNumberOfEmployes() == null)
				|| (org.getContact().isEmpty() || org.getCountry().isEmpty() || org.getEmail().isEmpty()
						|| org.getLocationOfOrganization().isEmpty() || org.getNameOfOrganization().isEmpty()
						|| org.getNumberOfEmployes().isEmpty())) {
			throw new MandatoryFeildExcetion("Fill All Feilds properly");
		}
		if (repo.existsBynameOfOrganization(org.getNameOfOrganization())) {
			throw new OrganizationNameAlreadyExistsException("Organisation is already exists");
		}
		return repo.save(org);
	}
}
