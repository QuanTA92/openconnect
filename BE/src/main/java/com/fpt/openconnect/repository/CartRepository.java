package com.fpt.openconnect.repository;

import com.fpt.openconnect.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {

    List<CartEntity> findByUserEntityId(int idUser);
}
