package com.gcp.storage.controller;

import com.gcp.storage.model.Company;
import com.gcp.storage.service.CompanyService;
import com.gcp.storage.service.GCPStorageService;
import com.google.cloud.storage.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fpa")
public class CompanyController {

    @Autowired
    GCPStorageService gcpStorageService;

    @Autowired
    CompanyService companyService;

    @PostMapping("/company/create")
    public String createCompany(
            @RequestBody Company company
    ) {
        Bucket bucket = gcpStorageService.createBucket(company.getCompanyName());
        return companyService.createCompany(company);
    }
}
