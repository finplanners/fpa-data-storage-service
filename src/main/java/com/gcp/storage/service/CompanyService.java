package com.gcp.storage.service;

import com.gcp.storage.model.Company;
import com.gcp.storage.model.FPDetails;
import com.gcp.storage.repository.GCPStorageRepository;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private GCPStorageRepository gcpStorageRepository;

    public String createCompany(Company company) {
        Company companyCreated = (Company) gcpStorageRepository.save(company);
        return companyCreated.getCompanyName() + " is successfully created";
    }
}
