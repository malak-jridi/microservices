package org.comAndDev.companyservice.proxies;

import org.comAndDev.companyservice.entities.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "category-service")
public interface CategoryProxy {

    @GetMapping( "/Category/all/{id}")
    public Category findCategoryById(@PathVariable( name = "id") Long id);
}
