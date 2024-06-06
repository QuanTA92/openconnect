package com.fpt.openconnect.controller;

import com.fpt.openconnect.payload.response.BaseResponse;
import com.fpt.openconnect.payload.response.CategoryWorkshopResponse;
import com.fpt.openconnect.service.imp.CategoryWorkshopServiceImp;
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
@RequestMapping("/categoryworkshop")
public class CategoryWorkshopController {

    @Autowired
    private CategoryWorkshopServiceImp categoryWorkshopServiceImp;

    @GetMapping("")
    private ResponseEntity<?> getAllCategoryWorkshops() {
        List<CategoryWorkshopResponse> categoryWorkshopResponses = categoryWorkshopServiceImp.getAllCategoryWorkshop();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get all category workshops");
        baseResponse.setData(categoryWorkshopResponses);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
