package com.fpt.openconnect.repository;

import com.fpt.openconnect.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer>
{
    List<TicketEntity> findByWorkshop_Id(int idWorkshop);
}
