package com.fpt.openconnect.repository;

import com.fpt.openconnect.entity.WorkshopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<WorkshopEntity,Integer> {

    void deleteById(int id);
}
