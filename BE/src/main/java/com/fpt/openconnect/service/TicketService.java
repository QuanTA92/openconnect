package com.fpt.openconnect.service;

import com.fpt.openconnect.entity.TicketEntity;
import com.fpt.openconnect.entity.WorkshopEntity;
import com.fpt.openconnect.payload.response.TicketResponse;
import com.fpt.openconnect.repository.TicketRepository;
import com.fpt.openconnect.repository.WorkshopRepository;
import com.fpt.openconnect.service.imp.TicketServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements TicketServiceImp {

    @Value("${root.folder}")
    private String rootFolder;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    @Override
    public boolean createTicket(String nameTicket, double priceTicket, String descriptionTicket,
                                int quantityTicket, MultipartFile imageTicket, int idWorkshop) throws Exception {

        String pathImage = rootFolder + "\\" + imageTicket.getOriginalFilename();

        Path path = Paths.get(rootFolder);
        Path pathImageCopy = Paths.get(pathImage);

        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        Files.copy(imageTicket.getInputStream(), pathImageCopy, StandardCopyOption.REPLACE_EXISTING);

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setNameTicket(nameTicket);
        ticketEntity.setPriceTicket(priceTicket);
        ticketEntity.setDescriptionTicket(descriptionTicket);
        ticketEntity.setQuantityTicket(quantityTicket);
        ticketEntity.setImageTicket(pathImage);

        WorkshopEntity workshopEntity = workshopRepository.findById(idWorkshop).orElseThrow(() -> new RuntimeException("Workshop not found"));

        ticketEntity.setWorkshop(workshopEntity);
        ticketRepository.save(ticketEntity);
        return true;
    }

    @Override
    public List<TicketResponse> getAllTicketsByIdWorkshop(int idWorkshop) throws Exception {
        List<TicketEntity> ticketEntities = ticketRepository.findByWorkshop_Id(idWorkshop);

        List<TicketResponse> ticketResponses = new ArrayList<>();
        for (TicketEntity ticketEntity : ticketEntities) {
            TicketResponse ticketResponse = new TicketResponse();

            ticketResponse.setNameTicket(ticketEntity.getNameTicket());

            ticketResponse.setIdTicket(ticketResponse.getIdTicket());

            ticketResponse.setPriceTicket(ticketEntity.getPriceTicket());

            ticketResponse.setDescriptionTicket(ticketEntity.getDescriptionTicket());

            ticketResponse.setQuantityTicket(ticketEntity.getQuantityTicket());
            ticketResponse.setImageTicket(ticketEntity.getImageTicket());
            ticketResponse.setNameWorkshop(ticketEntity.getWorkshop().getNameWorkshop());
            ticketResponses.add(ticketResponse);
        }

        return ticketResponses;
    }

    @Override
    public List<TicketResponse> getTicketById(int idTicket) throws Exception {
        Optional<TicketEntity> ticketOptional = ticketRepository.findById(idTicket);
        if (ticketOptional.isPresent()) {
            TicketEntity ticketEntity = ticketOptional.get();
            TicketResponse ticketResponse = new TicketResponse();
            ticketResponse.setNameTicket(ticketEntity.getNameTicket());
            ticketResponse.setPriceTicket(ticketEntity.getPriceTicket());
            ticketResponse.setDescriptionTicket(ticketEntity.getDescriptionTicket());
            ticketResponse.setQuantityTicket(ticketEntity.getQuantityTicket());
            ticketResponse.setImageTicket(ticketEntity.getImageTicket());
            ticketResponse.setNameWorkshop(ticketEntity.getWorkshop().getNameWorkshop());
            ticketResponse.setIdTicket(ticketResponse.getIdTicket());
            return Collections.singletonList(ticketResponse);
        } else {
            throw new RuntimeException("Ticket not found with ID: " + idTicket);
        }
    }

    @Override
    public boolean deleteTicket(int idTicket) throws Exception {
        if (ticketRepository.existsById(idTicket)) {
            ticketRepository.deleteById(idTicket);
            return true;
        } else {
            throw new RuntimeException("Ticket not found with ID: " + idTicket);
        }
    }

    @Override
    public boolean updateTicketById(int idTicket, String nameTicket, double priceTicket, String descriptionTicket,
                                    int quantityTicket, MultipartFile imageTicket, int idWorkshop) throws Exception {
        Optional<TicketEntity> optionalTicketEntity = ticketRepository.findById(idTicket);
        if (optionalTicketEntity.isPresent()) {
            TicketEntity ticketEntity = optionalTicketEntity.get();
            ticketEntity.setNameTicket(nameTicket);
            ticketEntity.setPriceTicket(priceTicket);
            ticketEntity.setDescriptionTicket(descriptionTicket);
            ticketEntity.setQuantityTicket(quantityTicket);
            if (imageTicket != null && !imageTicket.isEmpty()) {
                String pathImage = rootFolder + "\\" + imageTicket.getOriginalFilename();
                Path path = Paths.get(rootFolder);
                Path pathImageCopy = Paths.get(pathImage);
                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                }
                Files.copy(imageTicket.getInputStream(), pathImageCopy, StandardCopyOption.REPLACE_EXISTING);
                ticketEntity.setImageTicket(pathImage);
            }
            WorkshopEntity workshopEntity = workshopRepository.findById(idWorkshop).orElseThrow(() -> new RuntimeException("Workshop not found"));
            ticketEntity.setWorkshop(workshopEntity);
            ticketRepository.save(ticketEntity);
            return true;
        } else {
            throw new RuntimeException("Ticket not found with ID: " + idTicket);
        }
    }
}
