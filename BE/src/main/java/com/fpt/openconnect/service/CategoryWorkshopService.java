package com.fpt.openconnect.service;

import com.fpt.openconnect.entity.CategoryWorkshopEntity;
import com.fpt.openconnect.entity.WorkshopEntity;
import com.fpt.openconnect.payload.response.CategoryWorkshopResponse;
import com.fpt.openconnect.repository.CategoryWorkshopRepository;
import com.fpt.openconnect.service.imp.CategoryWorkshopServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryWorkshopService implements CategoryWorkshopServiceImp {

    @Autowired
    private CategoryWorkshopRepository categoryWorkshopRepository;


    @Override
    public List<CategoryWorkshopResponse> getAllCategoryWorkshop() {
        List<CategoryWorkshopEntity> categoryWorkshopEntities = categoryWorkshopRepository.findAll();
        List<CategoryWorkshopResponse> categoryWorkshopResponses = new ArrayList<>();

        for (CategoryWorkshopEntity categoryWorkshopEntity : categoryWorkshopEntities) {
            CategoryWorkshopResponse categoryWorkshopResponse = new CategoryWorkshopResponse();
            categoryWorkshopResponse.setIdCategoryWorkshop(categoryWorkshopEntity.getId());
            categoryWorkshopResponse.setNameCategoryWorkshop(categoryWorkshopEntity.getNameCategoryWorkshop());

            categoryWorkshopResponses.add(categoryWorkshopResponse);
        }

        return categoryWorkshopResponses;

    }

    @Override
    public List<CategoryWorkshopResponse> getWorkshopByIdCategory(int categoryId) {
        // Tạo một danh sách để lưu các CategoryWorkshopResponse
        List<CategoryWorkshopResponse> categoryWorkshopResponses = new ArrayList<>();

        // Tìm kiếm category dựa trên id
        Optional<CategoryWorkshopEntity> categoryOptional = categoryWorkshopRepository.findById(categoryId);

        // Kiểm tra xem category có tồn tại không
        if (categoryOptional.isPresent()) {
            CategoryWorkshopEntity category = categoryOptional.get();

            // Lấy danh sách các workshop từ category
            List<WorkshopEntity> workshops = category.getWorkshopEntities();

            // Lặp qua danh sách các workshop để tạo các CategoryWorkshopResponse tương ứng
            for (WorkshopEntity workshop : workshops) {
                CategoryWorkshopResponse categoryWorkshopResponse = new CategoryWorkshopResponse();
                categoryWorkshopResponse.setIdCategoryWorkshop(category.getId());
                categoryWorkshopResponse.setNameCategoryWorkshop(category.getNameCategoryWorkshop());
                categoryWorkshopResponse.setIdWorkshop(workshop.getId());
                categoryWorkshopResponse.setNameWorkshop(workshop.getNameWorkshop());
                categoryWorkshopResponse.setDescriptionWorkshop(workshop.getDescriptionWorkshop());
                categoryWorkshopResponse.setTimeWorkshop(workshop.getTimeWorkshop());
                categoryWorkshopResponse.setImageWorkshop(workshop.getImageWorkshop());

                // Thêm CategoryWorkshopResponse vào danh sách
                categoryWorkshopResponses.add(categoryWorkshopResponse);
            }
        }

        // Trả về danh sách các CategoryWorkshopResponse
        return categoryWorkshopResponses;
    }

}
