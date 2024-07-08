package com.fpt.openconnect.service;

import com.fpt.openconnect.entity.OrdersEntity;
import com.fpt.openconnect.entity.ProductTicketOrderEntity;
import com.fpt.openconnect.payload.response.OrderDetailProductResponse;
import com.fpt.openconnect.payload.response.OrderDetailTicketResponse;
import com.fpt.openconnect.payload.response.OrdersResponse;
import com.fpt.openconnect.repository.OrdersRepository;
import com.fpt.openconnect.repository.ProductTicketOrderRepository;
import com.fpt.openconnect.repository.UserRepository;
import com.fpt.openconnect.service.imp.OrdersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdersService implements OrdersServiceImp {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductTicketOrderRepository productTicketOrderRepository;

    @Override
    public List<OrdersResponse> getOrderByIdUser(int idUser) {
        List<OrdersResponse> ordersResponses = new ArrayList<>();
        List<OrdersEntity> ordersEntities = ordersRepository.findByUserId(idUser);

        for (OrdersEntity order : ordersEntities) {
            OrdersResponse ordersResponse = new OrdersResponse();
            ordersResponse.setIdUser(order.getUser().getId());
            ordersResponse.setIdOrder(order.getId());
            ordersResponse.setPriceTotal(order.getPrice());
            ordersResponse.setNameStatus(order.getStatus().getName());
            ordersResponse.setCreateDate(String.valueOf(order.getCreateDate()));

            List<OrderDetailTicketResponse> orderDetailsTicket = new ArrayList<>();
            List<OrderDetailProductResponse> orderDetailsProduct = new ArrayList<>();

            for (ProductTicketOrderEntity productTicketOrder : order.getProductTicketOrderEntities()) {
                if (productTicketOrder != null) {
                    // Process ticket details
                    if (productTicketOrder.getKeys().getIdTicket() != null) {
                        OrderDetailTicketResponse ticketDetail = new OrderDetailTicketResponse();
                        ticketDetail.setIdTicket(productTicketOrder.getKeys().getIdTicket());
                        if (productTicketOrder.getTicketEntity() != null) {
                            ticketDetail.setNameWorkshop(productTicketOrder.getTicketEntity().getWorkshop().getNameWorkshop());
                            ticketDetail.setPriceTicket(productTicketOrder.getTicketEntity().getPriceTicket());
                        }
                        ticketDetail.setQuantityTicket(productTicketOrder.getQuantityTicket());
                        orderDetailsTicket.add(ticketDetail);
                    }

                    // Process product details
                    if (productTicketOrder.getKeys().getIdProduct() != null) {
                        OrderDetailProductResponse productDetail = new OrderDetailProductResponse();
                        productDetail.setIdProduct(productTicketOrder.getKeys().getIdProduct());
                        if (productTicketOrder.getProductEntity() != null) {
                            productDetail.setNameProduct(productTicketOrder.getProductEntity().getName());
                            productDetail.setPriceProduct(productTicketOrder.getProductEntity().getPrice());
                        }
                        productDetail.setQuantityProduct(productTicketOrder.getQuantityProduct());
                        orderDetailsProduct.add(productDetail);
                    }
                }
            }

            // Set order details into response object
            ordersResponse.setOrderDetailsTicket(orderDetailsTicket);
            ordersResponse.setOrderDetailsProduct(orderDetailsProduct);

            ordersResponses.add(ordersResponse);
        }

        return ordersResponses;
    }



}
