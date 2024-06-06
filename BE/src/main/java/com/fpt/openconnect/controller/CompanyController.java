package com.fpt.openconnect.controller;

import com.fpt.openconnect.payload.response.BaseResponse;
import com.fpt.openconnect.payload.response.CompanyResponse;
import com.fpt.openconnect.service.imp.CompanyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyServiceImp companyServiceImp;

    @GetMapping("")
    public ResponseEntity<BaseResponse> getAllCompany() {
        BaseResponse response = new BaseResponse();
        try {
            List<CompanyResponse> companies = companyServiceImp.getAllCompany();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Success");
            response.setData(companies);
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error retrieving companies");
            response.setError(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
