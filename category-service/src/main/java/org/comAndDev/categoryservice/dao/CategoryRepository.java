package org.comAndDev.categoryservice.dao;

import org.comAndDev.categoryservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CategoryRepository  extends JpaRepository<Category, Long> {
    public Category findCategoryById(Long id);
    public List<Category> findByArchivedFalse();
}
