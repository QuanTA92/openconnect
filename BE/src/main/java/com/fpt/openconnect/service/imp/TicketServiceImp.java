package com.fpt.openconnect.service.imp;

import com.fpt.openconnect.payload.response.TicketResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TicketServiceImp {

    boolean createTicket(String nameTicket, double priceTicket, String descriptionTicket,
                         int quantityTicket, MultipartFile imageTicket, int idWorkshop) throws Exception;

    List<TicketResponse> getAllTicketsByIdWorkshop(int idWorkshop) throws Exception;

    List<TicketResponse> getTicketById(int idTicket) throws Exception;

    boolean deleteTicket(int idTicket) throws Exception;

    boolean updateTicketById(int idTicket, String nameTicket, double priceTicket, String descriptionTicket,
                         int quantityTicket, MultipartFile imageTicket, int idWorkshop) throws Exception;
}
