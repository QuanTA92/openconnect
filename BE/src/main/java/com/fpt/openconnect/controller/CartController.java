package com.fpt.openconnect.controller;

import com.fpt.openconnect.payload.response.BaseResponse;
import com.fpt.openconnect.payload.request.CartRequest;
import com.fpt.openconnect.payload.response.CartResponse;
import com.fpt.openconnect.service.imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartServiceImp cartServiceImp;

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertCart(@RequestBody CartRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            boolean success = cartServiceImp.insertCart(
                    request.getQuantityProduct(),
                    request.getQuantityTicket(),
                    request.getIdUser(),
                    request.getIdProduct(),
                    request.getIdTicket()
            );
            if (success) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("Cart item added successfully");
            } else {
                response.setStatusCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("Failed to add cart item");
            }
        } catch (IOException e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error occurred while adding cart item");
            response.setError(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<BaseResponse> getCartByIdUser(@PathVariable int idUser) {
        BaseResponse response = new BaseResponse();
        HttpStatus httpStatus;

        try {
            List<CartResponse> cartResponses = cartServiceImp.getCartByIdUser(idUser);
            if (cartResponses != null && !cartResponses.isEmpty()) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("Cart items retrieved successfully");
                response.setData(cartResponses);
            } else {
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("No cart items found for the user");
            }
            httpStatus = HttpStatus.valueOf(response.getStatusCode());
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal Server Error: " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }



    @DeleteMapping("/{idCart}")
    public ResponseEntity<BaseResponse> deleteCartById(@PathVariable int idCart) {
        BaseResponse response = new BaseResponse();
        HttpStatus httpStatus;

        try {
            boolean result = cartServiceImp.deleteCartById(idCart);
            if (result) {
                response.setStatusCode(200);
                response.setMessage("Successfully deleted cart item.");
                httpStatus = HttpStatus.OK;
            } else {
                response.setStatusCode(404);
                response.setMessage("Cart item not found.");
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Internal Server Error: " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }



}
