package com.fpt.openconnect.controller;

import com.fpt.openconnect.payload.response.BaseResponse;
import com.fpt.openconnect.payload.response.OrdersResponse;
import com.fpt.openconnect.service.imp.OrdersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersServiceImp ordersServiceImp;

    @GetMapping("/{idUser}")
    public ResponseEntity<BaseResponse> getOrderByIdUser(@PathVariable int idUser) {
        BaseResponse response = new BaseResponse();
        HttpStatus httpStatus;
        try {
            List<OrdersResponse> orders = ordersServiceImp.getOrderByIdUser(idUser);
            if (orders != null && !orders.isEmpty()) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("Successfully retrieved orders for user with id " + idUser);
                response.setData(orders);
                httpStatus = HttpStatus.OK;
            } else {
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("No orders found for user with id " + idUser);
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to retrieve orders: " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(response, httpStatus);
    }




}
