package com.sira.employe_monitoring_system.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sira.employe_monitoring_system.entity.Company;
import com.sira.employe_monitoring_system.exception.MandatoryFeildExcetion;
import com.sira.employe_monitoring_system.exception.OrganizationNameAlreadyExistsException;
import com.sira.employe_monitoring_system.exception.ResourceNotFoundException;
import com.sira.employe_monitoring_system.repository.CompanyRepository;

@Service
public class CompanyServiceImplimentation implements CompanyService {

	@Autowired
	private CompanyRepository repo;

	@Autowired
	private BCryptPasswordEncoder encode;

	// Organization Registration
	@Override
	public Company addOrganistaion(Company company) {
		if (company.getContact().isEmpty() || company.getContact() == null || company.getCountry().isEmpty()
				|| company.getCountry() == null || company.getEmail().isEmpty()
				|| company.getEmail() == null || company.getLocationOfOrganization().isEmpty()
				|| company.getLocationOfOrganization() == null || company.getCompany().isEmpty()
				|| company.getCompany() == null || company.getNoOfEmp().isEmpty()
				|| company.getNoOfEmp() == null) {
			throw new MandatoryFeildExcetion("All Feilds are Mandatory");
		}
		if (repo.existsByemail(company.getEmail())) {
			throw new OrganizationNameAlreadyExistsException("Organization with this mail is already Registered");
		}
		return repo.save(company);
	}

	// All Companies
	//@Override
//	public Map getAllCompanies() {
//		List<Company> list = repo.findAll();
//		Map<String, Map<String, String>> companiesMap = new HashMap<>();
//
//		if (list.size() != 0) {
//			Iterator<Company> iterator = list.iterator();
//
//			while (iterator.hasNext()) {
//				Company company = iterator.next();
//
//				
//				if (!company.isDeleted()) {
//					Map<String, String> companyDetails = new HashMap<>();
//					companyDetails.put("id", company.get_id());
//					companyDetails.put("company", company.getCompany());
//					companyDetails.put("email", company.getEmail());
//					companyDetails.put("locationOfOrganization", company.getLocationOfOrganization());
//					companyDetails.put("contact", company.getContact());
//					companyDetails.put("noOfEmp", company.getNoOfEmp());
//					companyDetails.put("country", company.getCountry());
//					companiesMap.put(company.getCompany(), companyDetails);
//				}
//			}
//
//			return companiesMap;
//		}
//
//		return null;
//	}

	@Override
	public Company editCompany(String _id, Company company) {

		Optional<Company> optional = repo.findById(_id);
		if (optional.isEmpty()) {
			 throw new ResourceNotFoundException("Organization not found with Name : "+_id);
		}
		Company organization = optional.get();
		organization.setContact(company.getContact());
		organization.setCountry(company.getCountry());
		organization.setEmail(company.getEmail());
		organization.setLocationOfOrganization(company.getLocationOfOrganization());
		organization.setCompany(company.getCompany());
		organization.setNoOfEmp(company.getNoOfEmp());
		organization.setNoOfAdmins(company.getNoOfAdmins());

		return repo.save(organization);
	}

	@Override
	public boolean deleteCompany(String name) {
		Company company = repo.findByCompany(name);
		if (company == null) {
			return false;
		}
		company.setDeleted(true);
		repo.save(company);
		return true;
	}

	@Override
	public Company getCompanyByName(String name) {
		
		 Optional<Company> optional = repo.findById(name);
		 if(optional.isPresent())
		 {
			 return optional.get();
		 }
		 return null;
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> list = repo.findByIsDeletedFalse();
		if(list.size() != 0)
		{
			return list;
		}
		else
		{
			return null;
		}
	}

}
