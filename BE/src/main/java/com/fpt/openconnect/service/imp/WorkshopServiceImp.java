package com.fpt.openconnect.service.imp;

import com.fpt.openconnect.payload.response.WorkshopResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface WorkshopServiceImp {

    boolean createWorkshop(String nameWorkshop, String descriptionWorkshop,
                           String timeWorkshop, String addressWorkshop, MultipartFile imageWorkshop,
                           int idCompanyWorkshop, int idCategoryWorkshop) throws IOException;

    List<WorkshopResponse> getAllWorkshop();

    List<WorkshopResponse> getWorkshopById(int idWorkshop);

    boolean deleteWorkshopById(int idWorkshop);

    boolean updateWorkshopById(int idWorkshop, String nameWorkshop, String descriptionWorkshop,
                               String timeWorkshop, String addressWorkshop, MultipartFile imageWorkshop,
                               int idCompanyWorkshop, int idCategoryWorkshop) throws IOException;

    List<WorkshopResponse> getWorkshopByIdCategory(int idCategoryWorkshop);
}
