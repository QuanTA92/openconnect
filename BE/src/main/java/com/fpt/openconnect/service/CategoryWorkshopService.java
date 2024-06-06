package com.fpt.openconnect.service;

import com.fpt.openconnect.entity.CategoryWorkshopEntity;
import com.fpt.openconnect.payload.response.CategoryWorkshopResponse;
import com.fpt.openconnect.repository.CategoryWorkshopRepository;
import com.fpt.openconnect.service.imp.CategoryWorkshopServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
