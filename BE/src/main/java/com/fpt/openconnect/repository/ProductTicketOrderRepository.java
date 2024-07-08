package com.fpt.openconnect.repository;

import com.fpt.openconnect.entity.ProductTicketOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTicketOrderRepository extends JpaRepository<ProductTicketOrderEntity, Integer> {

    List<ProductTicketOrderEntity> findByKeysIdOrder(int id);
}
