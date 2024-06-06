package com.fpt.openconnect.service;

import com.fpt.openconnect.entity.CompanyEntity;
import com.fpt.openconnect.payload.response.CompanyResponse;
import com.fpt.openconnect.repository.CompanyRepository;
import com.fpt.openconnect.service.imp.CompanyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService implements CompanyServiceImp {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<CompanyResponse> getAllCompany() {
        List<CompanyEntity> companies = companyRepository.findAll();
        return companies.stream()
                .map(this::mapToCompanyResponse)
                .collect(Collectors.toList());
    }

    private CompanyResponse mapToCompanyResponse(CompanyEntity company) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setIdCompany(company.getId());
        companyResponse.setNameCompany(company.getNameCompany());
        companyResponse.setDescriptionCompany(company.getDescriptionCompany());
        companyResponse.setAddressCompany(company.getAddressCompany());
        companyResponse.setImageCompany(company.getImageCompany());
        return companyResponse;
    }

}
