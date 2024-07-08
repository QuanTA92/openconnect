package com.fpt.openconnect.service.imp;

import com.fpt.openconnect.payload.response.OrdersResponse;

import java.util.List;

public interface OrdersServiceImp {

    List<OrdersResponse> getOrderByIdUser(int idUser);

}
