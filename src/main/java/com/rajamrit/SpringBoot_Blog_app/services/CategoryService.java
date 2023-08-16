package com.rajamrit.SpringBoot_Blog_app.services;

import com.rajamrit.SpringBoot_Blog_app.entities.Category;
import com.rajamrit.SpringBoot_Blog_app.payloads.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    // create
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    // update
    CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId);

    // delete
    void deleteCategory(int categoryId);

    // get
    CategoryDTO getCategory(int categoryId);

    // get all
    List<CategoryDTO> getAllCategory();

}
