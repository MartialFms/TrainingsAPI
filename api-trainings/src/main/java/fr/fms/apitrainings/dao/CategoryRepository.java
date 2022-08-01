package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
