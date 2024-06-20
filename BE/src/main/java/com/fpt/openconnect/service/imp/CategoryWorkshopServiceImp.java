package com.fpt.openconnect.service.imp;

import com.fpt.openconnect.payload.response.CategoryWorkshopResponse;

import java.util.List;

public interface CategoryWorkshopServiceImp {

    List<CategoryWorkshopResponse> getAllCategoryWorkshop();

    List<CategoryWorkshopResponse> getWorkshopByIdCategory(int idCategory);
}
