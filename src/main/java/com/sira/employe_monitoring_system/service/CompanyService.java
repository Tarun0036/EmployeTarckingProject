package com.sira.employe_monitoring_system.service;

import java.util.List;
import java.util.Map;

import com.sira.employe_monitoring_system.entity.Company;

public interface CompanyService {

	Company addOrganistaion(Company company);
	
	Map getAllCompanies();
	
	Company editCompany(String name, Company company);
	
	boolean deleteCompany(String name);
	
	Company getCompanyByName(String name);
}
