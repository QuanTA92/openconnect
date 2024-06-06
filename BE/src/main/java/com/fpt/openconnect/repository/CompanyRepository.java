package com.fpt.openconnect.repository;

import com.fpt.openconnect.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {
}
