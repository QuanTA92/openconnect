package com.fpt.openconnect.repository;

import com.fpt.openconnect.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {

    List<OrdersEntity> findByUserId(int userId);

    //orderReturn
    Optional<OrdersEntity> findFirstByUserIdOrderByCreateDateDesc(int userId);
}
