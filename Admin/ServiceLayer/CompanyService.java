package com.example.SuperAdmin.ServiceLayer;

import java.util.List;

import com.example.SuperAdmin.Model.Company;

public interface CompanyService {
	Company getOrganizationBy_Id(String _id);

	List<Company> getAllOrganization();

	Company updateCompanyBy_id(String _id, Company details);

	boolean softDeleteCompanyBy_id(String _id);

	Company createCompany(Company details);

}
