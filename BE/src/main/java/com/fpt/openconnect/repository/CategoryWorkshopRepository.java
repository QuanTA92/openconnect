package com.fpt.openconnect.repository;

import com.fpt.openconnect.entity.CategoryWorkshopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryWorkshopRepository extends JpaRepository<CategoryWorkshopEntity, Integer> {
}
