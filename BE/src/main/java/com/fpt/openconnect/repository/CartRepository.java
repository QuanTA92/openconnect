package com.fpt.openconnect.repository;

import com.fpt.openconnect.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {

    List<CartEntity> findByUserEntityId(int idUser);

    List<CartEntity> findAllByUserEntityId(int idUser);
}
