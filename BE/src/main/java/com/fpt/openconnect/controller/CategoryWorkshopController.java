package com.fpt.openconnect.controller;

import com.fpt.openconnect.payload.response.BaseResponse;
import com.fpt.openconnect.payload.response.CategoryWorkshopResponse;
import com.fpt.openconnect.service.imp.CategoryWorkshopServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{idCategory}")
    private ResponseEntity<?> getWorkshopsByCategoryId(@PathVariable int idCategory) {
        // Sử dụng CategoryWorkshopServiceImp để lấy danh sách các workshop dựa trên id của category
        List<CategoryWorkshopResponse> workshopResponses = categoryWorkshopServiceImp.getWorkshopByIdCategory(idCategory);

        if (workshopResponses.isEmpty()) {
            // Trả về lỗi 404 nếu không tìm thấy workshop nào cho category có id tương ứng
            return new ResponseEntity<>("No workshops found for category with id: " + idCategory, HttpStatus.NOT_FOUND);
        }

        // Nếu tìm thấy, tạo BaseResponse và trả về danh sách các workshop
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Get workshops by category id");
        baseResponse.setData(workshopResponses);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


}
