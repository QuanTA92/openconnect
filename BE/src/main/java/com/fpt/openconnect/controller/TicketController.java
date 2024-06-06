package com.fpt.openconnect.controller;

import com.fpt.openconnect.payload.response.BaseResponse;
import com.fpt.openconnect.payload.response.TicketResponse;
import com.fpt.openconnect.service.TicketService;
import com.fpt.openconnect.service.imp.TicketServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketServiceImp ticketServiceImp;

    @PostMapping("/createTicket")
    public ResponseEntity<BaseResponse> createTicket(@RequestParam String nameTicket, @RequestParam double priceTicket,
                                                     @RequestParam String descriptionTicket, @RequestParam int quantityTicket,
                                                     MultipartFile imageTicket, @RequestParam int idWorkshop) throws Exception {

        boolean isCreated = ticketServiceImp.createTicket(nameTicket, priceTicket, descriptionTicket, quantityTicket, imageTicket, idWorkshop);

        BaseResponse response = new BaseResponse();
        if (isCreated) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Workshop created successfully");
            return ResponseEntity.ok(response);
        } else {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Failed to create workshop");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/getAllTicketsByIdWorkshop/{idWorkshop}")
    public ResponseEntity<BaseResponse> getAllTicketsByIdWorkshop(@PathVariable int idWorkshop) {
        try {
            List<TicketResponse> tickets = ticketService.getAllTicketsByIdWorkshop(idWorkshop);
            BaseResponse response = new BaseResponse();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Tickets retrieved successfully");
            response.setData(tickets);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BaseResponse response = new BaseResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to retrieve tickets due to an internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getTicketById/{idTicket}")
    public ResponseEntity<BaseResponse> getTicketById(@PathVariable int idTicket) {
        try {
            List<TicketResponse> ticket = ticketService.getTicketById(idTicket);
            BaseResponse response = new BaseResponse();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Ticket retrieved successfully");
            response.setData(ticket);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            BaseResponse response = new BaseResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to retrieve ticket due to an internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{idTicket}")
    public ResponseEntity<BaseResponse> deleteTicket(@PathVariable int idTicket) {
        try {
            boolean isDeleted = ticketService.deleteTicket(idTicket);
            BaseResponse response = new BaseResponse();
            if (isDeleted) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("Ticket deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("Ticket not found with ID: " + idTicket);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            BaseResponse response = new BaseResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to delete ticket due to an internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/updateTicket/{idTicket}")
    public ResponseEntity<BaseResponse> updateTicketById(@PathVariable int idTicket,
                                                         @RequestParam String nameTicket,
                                                         @RequestParam double priceTicket,
                                                         @RequestParam String descriptionTicket,
                                                         @RequestParam int quantityTicket,
                                                         @RequestParam(required = false) MultipartFile imageTicket,
                                                         @RequestParam int idWorkshop) {
        try {
            boolean isUpdated = ticketService.updateTicketById(idTicket, nameTicket, priceTicket, descriptionTicket, quantityTicket, imageTicket, idWorkshop);
            BaseResponse response = new BaseResponse();
            if (isUpdated) {
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("Ticket updated successfully");
                return ResponseEntity.ok(response);
            } else {
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("Ticket not found with ID: " + idTicket);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            BaseResponse response = new BaseResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Failed to update ticket due to an internal server error");
            response.setError(e.getMessage()); // Gán thông tin chi tiết lỗi vào đây
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
