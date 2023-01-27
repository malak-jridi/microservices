package org.comAndDev.categoryservice.service;

import org.comAndDev.categoryservice.dao.CategoryRepository;
import org.comAndDev.categoryservice.entities.Category;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(String name, String description) {
        Category category=new Category();
        category.setName(name);
        category.setDescription(description);
        category.setCreatedAt(LocalDate.now());
        categoryRepository.save(category);
        return category ;
    }

    @Override
    public Category update(long id, String name, String description) {
        Category category= categoryRepository.findCategoryById(id);
        category.setName(name);
        category.setDescription(description);
        category.setUpdateAt(LocalDate.now());
        categoryRepository.save(category);
        return category ;
    }

    @Override
    public Category archived(long id) {
        Category category= categoryRepository.findCategoryById(id);
        category.setArchived(true);
        categoryRepository.save(category);
        return category ;
    }

    @Override
    public List<Category> AllCategories() {

        List<Category> categories = categoryRepository.findByArchivedFalse();
        return categories;
    }
    @Override
    public Category findById(long id) {
        Category category= categoryRepository.findCategoryById(id);
        return category ;
    }
}
