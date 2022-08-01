package fr.fms.apitrainings.controller;

import fr.fms.apitrainings.entities.Category;
import fr.fms.apitrainings.service.ImplCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ImplCategoryService implCategoryService;

    @GetMapping("/categories")
    public List<Category> listOfCategories(){
        return implCategoryService.getAll();
    }

    @GetMapping("/category/{id}")
    public Category getArticlesByCat(@PathVariable("id") long id){
        Optional<Category> category = implCategoryService.getOneById(id);
        if(category.isPresent()){
            return category.get();
        }
        return null;
    }
}
