package com.fpt.openconnect.controller;

import com.fpt.openconnect.payload.response.BaseResponse;
import com.fpt.openconnect.payload.response.WorkshopResponse;
import com.fpt.openconnect.service.WorkshopService;
import com.fpt.openconnect.service.imp.WorkshopServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/workshop")
public class WorkshopController {

    @Autowired
    private WorkshopServiceImp workshopServiceImp;

    @Autowired
    private WorkshopService workshopService;

    @PostMapping("/createWorkshop")
    public ResponseEntity<BaseResponse> createWorkshop(@RequestParam String workshopName,
                                                       @RequestParam String descriptionWorkshop,
                                                       @RequestParam String timeWorkshop,
                                                       @RequestParam String addressWorkshop,
                                                       @RequestParam MultipartFile imageWorkshop,
                                                       @RequestParam int idCompanyWorkshop,
                                                       @RequestParam int idCategoryWorkshop) throws IOException {

        boolean isCreated = workshopServiceImp.createWorkshop(workshopName, descriptionWorkshop, timeWorkshop, addressWorkshop, imageWorkshop, idCompanyWorkshop, idCategoryWorkshop);

        BaseResponse response = new BaseResponse();
        if (isCreated) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Workshop created successfully");
            return ResponseEntity.ok(response);
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Failed to create workshop");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> getAllWorkshops() {
        List<WorkshopResponse> workshops = workshopService.getAllWorkshop();

        BaseResponse response = new BaseResponse();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("List of all workshops");
        response.setData(workshops);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idWorkshop}")
    public ResponseEntity<BaseResponse> getWorkshopById(@PathVariable int idWorkshop) {
        List<WorkshopResponse> workshopResponses = workshopServiceImp.getWorkshopById(idWorkshop);

        BaseResponse response = new BaseResponse();
        if (!workshopResponses.isEmpty()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Workshop found");
            response.setData(workshopResponses);
            return ResponseEntity.ok(response);
        } else {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Workshop not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{idWorkshop}")
    public ResponseEntity<?> deleteWorkshopById(@PathVariable int idWorkshop) {
        boolean isDeleted = workshopServiceImp.deleteWorkshopById(idWorkshop);
        if (isDeleted) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Product deleted successfully");
            baseResponse.setStatusCode(200);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found or unable to delete", HttpStatus.OK);
        }
    }

    @PutMapping("/{idWorkshop}")
    public ResponseEntity<BaseResponse> updateWorkshopById(@PathVariable int idWorkshop,
                                                           @RequestParam String nameWorkshop,
                                                           @RequestParam String descriptionWorkshop,
                                                           @RequestParam String timeWorkshop,
                                                           @RequestParam String addressWorkshop,
                                                           @RequestParam(required = false) MultipartFile imageWorkshop,
                                                           @RequestParam int idCompanyWorkshop,
                                                           @RequestParam int idCategoryWorkshop) throws IOException {
        try {
            boolean isUpdated = workshopServiceImp.updateWorkshopById(idWorkshop, nameWorkshop, descriptionWorkshop, timeWorkshop, addressWorkshop, imageWorkshop, idCompanyWorkshop, idCategoryWorkshop);
            BaseResponse response = new BaseResponse();
            if (isUpdated) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("Workshop updated successfully");
                return ResponseEntity.ok(response);
            } else {
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("Workshop not found with ID: " + idWorkshop);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            BaseResponse response = new BaseResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to update workshop due to an internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
