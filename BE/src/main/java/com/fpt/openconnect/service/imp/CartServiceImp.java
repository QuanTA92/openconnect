package com.fpt.openconnect.service.imp;

import com.fpt.openconnect.entity.CartEntity;
import com.fpt.openconnect.payload.response.CartResponse;

import java.io.IOException;
import java.util.List;

public interface CartServiceImp {

    boolean insertCart(int quantityProduct, int quantityTicket,
                       int idUser, int idProduct, int idTicket) throws IOException;

    List<CartResponse> getCartByIdUser(int idUser);

    boolean deleteCartById(int idCart);

}
