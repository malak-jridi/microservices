package org.comAndDev.categoryservice.service;

import org.comAndDev.categoryservice.entities.Category;

import java.util.List;

public interface CategoryService {
    public Category save(String name, String description);
    public Category update(long id, String name, String description);
    public Category archived(long id);
    public List<Category> AllCategories();
    public Category findById(long id);

    }
