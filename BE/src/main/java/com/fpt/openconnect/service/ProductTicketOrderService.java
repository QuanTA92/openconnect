package com.fpt.openconnect.service;

import com.fpt.openconnect.repository.CartRepository;
import com.fpt.openconnect.repository.ProductTicketOrderRepository;
import com.fpt.openconnect.service.imp.ProductTicketOrderImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTicketOrderService implements ProductTicketOrderImp {

    @Autowired
    private ProductTicketOrderRepository productTicketOrderRepository;

    @Autowired
    private CartRepository cartRepository;

}
