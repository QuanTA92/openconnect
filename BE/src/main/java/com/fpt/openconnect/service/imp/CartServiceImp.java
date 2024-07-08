package com.fpt.openconnect.service.imp;

import com.fpt.openconnect.entity.CartEntity;
import com.fpt.openconnect.payload.response.CartResponse;

import java.io.IOException;
import java.util.List;

public interface CartServiceImp {

    boolean insertCart(Integer quantityProduct, Integer quantityTicket,
                       int idUser, int idProduct, int idTicket) throws IOException;


    boolean deleteCartById(int idCart);

    List<CartResponse> getCartByIdUser(int idUser);

    // checkout
    List<CartEntity> getCartsByUserId(int idUser);
}
