package org.comAndDev.categoryservice.web;

import lombok.Data;
import org.comAndDev.categoryservice.entities.Category;
import org.comAndDev.categoryservice.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
@RestController
@RequestMapping("/Category")
@CrossOrigin(origins="http://localhost:3000")
public class CategoryController {
    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/Save")
    public ResponseEntity<Void> SaveCategory(@RequestBody CategoryForm categoryForm ){
        logger.info("Running from info service ....");
        Category category =    categoryService.save(categoryForm.getName(), categoryForm.getDescription());
        if (category == null)
            return ResponseEntity.noContent().build();
        logger.info("Running from info service ....");

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/Update")
    public ResponseEntity<Void> UpdateCategory(@RequestBody CategoryForm categoryForm) {

        Category category =  categoryService.update(categoryForm.getId(), categoryForm.getName(), categoryForm.getDescription());

        if (category == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/Archived")
    public ResponseEntity<Void> ArchivedCategory(@RequestBody CategoryForm categoryForm) {

        Category category =  categoryService.archived(categoryForm.getId());

        if (category == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/All")
    public List<Category> AllCategories() {
        List<Category> categories =  categoryService.AllCategories();
        return categories;
    }

    @GetMapping(value = "/all/{id}")
    public Category findCategoryById(@PathVariable("id") Long id) {
        Category category =  categoryService.findById(id);
        return category;
    }
}
@Data
class CategoryForm {
    private Long id;
    private String name;
    private String description;
}