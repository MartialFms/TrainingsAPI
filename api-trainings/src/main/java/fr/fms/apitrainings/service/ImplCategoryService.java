package fr.fms.apitrainings.service;

import fr.fms.apitrainings.dao.CategoryRepository;
import fr.fms.apitrainings.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplCategoryService implements IService<Category> {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getOneById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category obj) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
