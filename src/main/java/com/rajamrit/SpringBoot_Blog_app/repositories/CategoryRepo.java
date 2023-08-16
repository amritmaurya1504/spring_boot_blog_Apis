package com.rajamrit.SpringBoot_Blog_app.repositories;

import com.rajamrit.SpringBoot_Blog_app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
