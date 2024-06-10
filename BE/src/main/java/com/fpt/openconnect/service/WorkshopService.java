package com.fpt.openconnect.service;

import com.fpt.openconnect.entity.CategoryWorkshopEntity;
import com.fpt.openconnect.entity.CompanyEntity;
import com.fpt.openconnect.entity.WorkshopEntity;
import com.fpt.openconnect.payload.response.WorkshopResponse;
import com.fpt.openconnect.repository.CategoryWorkshopRepository;
import com.fpt.openconnect.repository.CompanyRepository;
import com.fpt.openconnect.repository.WorkshopRepository;
import com.fpt.openconnect.service.imp.WorkshopServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkshopService implements WorkshopServiceImp {

    @Value("${root.folder}")
    private String rootFolder;

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CategoryWorkshopRepository categoryWorkshopRepository;

    @Override
    public boolean createWorkshop(String workshopName, String descriptionWorkshop, String timeWorkshop, String addressWorkshop,
                                  MultipartFile imageWorkshop, int idCompanyWorkshop, int idCategoryWorkshop) throws IOException {

        String pathImage = rootFolder + "\\" + imageWorkshop.getOriginalFilename();

        Path path = Paths.get(rootFolder);
        Path pathImageCopy = Paths.get(pathImage);

        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        Files.copy(imageWorkshop.getInputStream(), pathImageCopy, StandardCopyOption.REPLACE_EXISTING);

        WorkshopEntity workshopEntity = new WorkshopEntity();
        workshopEntity.setNameWorkshop(workshopName);
        workshopEntity.setDescriptionWorkshop(descriptionWorkshop);
        workshopEntity.setTimeWorkshop(timeWorkshop);
        workshopEntity.setAddressWorkshop(addressWorkshop);
        workshopEntity.setImageWorkshop(pathImage);

        CompanyEntity companyWorkshop = companyRepository.findById(idCompanyWorkshop).orElseThrow(() -> new RuntimeException("Company not found"));

        // Đảm bảo rằng tên thuộc tính và cột khớp nhau
        CategoryWorkshopEntity categoryWorkshop = categoryWorkshopRepository.findById(idCategoryWorkshop).orElseThrow(() -> new RuntimeException("Category not found"));

        workshopEntity.setCompanyWorkshop(companyWorkshop);
        workshopEntity.setCategoryWorkshop(categoryWorkshop);

        workshopRepository.save(workshopEntity);

        return true;
    }

    @Override
    public List<WorkshopResponse> getAllWorkshop() {
        List<WorkshopEntity> workshops = workshopRepository.findAll();
        return workshops.stream()
                .map(this::mapToWorkshopResponse)
                .collect(Collectors.toList());
    }

    private WorkshopResponse mapToWorkshopResponse(WorkshopEntity workshop) {
        WorkshopResponse response = new WorkshopResponse();
        response.setIdWorkshop(String.valueOf(workshop.getId()));
        response.setNameWorkshop(workshop.getNameWorkshop());
        response.setDescriptionWorkshop(workshop.getDescriptionWorkshop());
        response.setTimeWorkshop(workshop.getTimeWorkshop());
        response.setAddressWorkshop(workshop.getAddressWorkshop());
        response.setImageWorkshop(workshop.getImageWorkshop());
        response.setNameCompanyWorkshop(workshop.getCompanyWorkshop().getNameCompany());
        response.setNameCategoryWorkshop(workshop.getCategoryWorkshop().getNameCategoryWorkshop());
        response.setImageCompanyWorkshop(workshop.getCompanyWorkshop().getImageCompany());
        response.setDescriptionCompanyWorkshop(workshop.getCompanyWorkshop().getDescriptionCompany());
        return response;
    }

    @Override
    public List<WorkshopResponse> getWorkshopById(int idWorkshop) {
        List<WorkshopResponse> workshopResponses = new ArrayList<>();

        Optional<WorkshopEntity> workshopOptional = workshopRepository.findById(idWorkshop);
        if (workshopOptional.isPresent()) {
            WorkshopEntity workshopEntity = workshopOptional.get();
            WorkshopResponse workshopResponse = new WorkshopResponse();
            workshopResponse.setIdWorkshop(String.valueOf(workshopEntity.getId()));
            workshopResponse.setNameWorkshop(workshopEntity.getNameWorkshop());
            workshopResponse.setDescriptionWorkshop(workshopEntity.getDescriptionWorkshop());
            workshopResponse.setTimeWorkshop(workshopEntity.getTimeWorkshop());
            workshopResponse.setAddressWorkshop(workshopEntity.getAddressWorkshop());
            workshopResponse.setImageWorkshop(workshopEntity.getImageWorkshop());
            workshopResponse.setNameCompanyWorkshop(workshopEntity.getCompanyWorkshop().getNameCompany());
            workshopResponse.setNameCategoryWorkshop(workshopEntity.getCategoryWorkshop().getNameCategoryWorkshop());
            workshopResponse.setImageCompanyWorkshop(workshopEntity.getCompanyWorkshop().getImageCompany());
            workshopResponse.setDescriptionCompanyWorkshop(workshopEntity.getCompanyWorkshop().getDescriptionCompany());

            workshopResponses.add(workshopResponse);
        }

        return workshopResponses;
    }

    @Override
    public boolean deleteWorkshopById(int idWorkshop) {
        if (workshopRepository.existsById(idWorkshop)) {
            workshopRepository.deleteById(idWorkshop); // Sử dụng phương thức xóa tùy chỉnh đã được thêm vào repository
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateWorkshopById(int idWorkshop, String nameWorkshop, String descriptionWorkshop,
                                      String timeWorkshop, String addressWorkshop, MultipartFile imageWorkshop,
                                      int idCompanyWorkshop, int idCategoryWorkshop) throws IOException {

        Optional<WorkshopEntity> workshopOptional = workshopRepository.findById(idWorkshop);
        if (workshopOptional.isPresent()) {
            WorkshopEntity workshopEntity = workshopOptional.get();
            workshopEntity.setNameWorkshop(nameWorkshop);
            workshopEntity.setDescriptionWorkshop(descriptionWorkshop);
            workshopEntity.setTimeWorkshop(timeWorkshop);
            workshopEntity.setAddressWorkshop(addressWorkshop);

            // Cập nhật ảnh nếu có
            if (imageWorkshop != null && !imageWorkshop.isEmpty()) {
                String pathImage = rootFolder + "\\" + imageWorkshop.getOriginalFilename();
                Path path = Paths.get(rootFolder);
                Path pathImageCopy = Paths.get(pathImage);

                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                }

                Files.copy(imageWorkshop.getInputStream(), pathImageCopy, StandardCopyOption.REPLACE_EXISTING);
                workshopEntity.setImageWorkshop(pathImage);
            }

            // Cập nhật công ty workshop
            CompanyEntity companyWorkshop = companyRepository.findById(idCompanyWorkshop)
                    .orElseThrow(() -> new RuntimeException("Company not found"));
            workshopEntity.setCompanyWorkshop(companyWorkshop);

            // Cập nhật loại workshop
            CategoryWorkshopEntity categoryWorkshop = categoryWorkshopRepository.findById(idCategoryWorkshop)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            workshopEntity.setCategoryWorkshop(categoryWorkshop);

            workshopRepository.save(workshopEntity);
            return true;
        } else {
            return false;  // Workshop không tồn tại
        }
    }

    @Override
    public List<WorkshopResponse> getWorkshopByIdCategory(int idCategoryWorkshop) {
        return List.of();
    }

}
