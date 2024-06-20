package com.fpt.openconnect.repository;

import com.fpt.openconnect.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer>
{
    List<TicketEntity> findByWorkshop_Id(int idWorkshop);
}
