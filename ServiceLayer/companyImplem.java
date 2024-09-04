package com.example.SuperAdmin.ServiceLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SuperAdmin.Exceptionhandling.OrganizationAlreadyExistsException;
import com.example.SuperAdmin.Model.Company;
import com.example.SuperAdmin.REpository.CompanyRepository;

@Service
public class companyImplem implements CompanyService {

	@Autowired
	public CompanyRepository repo;
	Map<String, String> map = new HashMap();

	@Override
	public Company updateCompanyBy_id(String _id, Company details) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean softDeleteCompanyBy_id(String _id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Company createCompany(Company details) {

		if (repo.existsBycompany(details.getCompany())) {
			throw new OrganizationAlreadyExistsException("Company Already exist");
		} else {
			return repo.save(details);
		}
	}

	@Override
	public List<Company> getAllOrganization() {
		List<Company> all = repo.findAll();
		List<Company> newlist = new ArrayList<>();
		for (Company details : all) {
			if (!details.isDeleted()) {
				newlist.add(details);
			}
		}
		return all;
	}

	@Override
	public Company getOrganizationBy_Id(String _id) {
		Company org = repo.findBy_id(_id);
		List<Company> newlist = new ArrayList<>();
		if (!org.isDeleted()) {
			newlist.add(org);
		}
		return org;
	}

}
