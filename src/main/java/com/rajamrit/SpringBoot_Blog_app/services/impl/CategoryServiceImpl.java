package com.rajamrit.SpringBoot_Blog_app.services.impl;

import com.rajamrit.SpringBoot_Blog_app.entities.Category;
import com.rajamrit.SpringBoot_Blog_app.exceptions.ResourceNotFoundException;
import com.rajamrit.SpringBoot_Blog_app.payloads.CategoryDTO;
import com.rajamrit.SpringBoot_Blog_app.repositories.CategoryRepo;
import com.rajamrit.SpringBoot_Blog_app.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category cat = this.modelMapper.map(categoryDTO, Category.class);
        Category addedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId) {
        Category oldCat = this.categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        oldCat.setCategoryTitle(categoryDTO.getCategoryTitle());
        oldCat.setCategoryDesc(categoryDTO.getCategoryDesc());

        Category updatedCat = this.categoryRepo.save(oldCat);
        return this.modelMapper.map(updatedCat, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category oldCat = this.categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepo.delete(oldCat);
    }

    @Override
    public CategoryDTO getCategory(int categoryId) {
        Category oldCat = this.categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        return this.modelMapper.map(oldCat, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> cat = this.categoryRepo.findAll();
        List<CategoryDTO> catDto = cat.stream().map(category -> this.modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
        return catDto;
    }
}
