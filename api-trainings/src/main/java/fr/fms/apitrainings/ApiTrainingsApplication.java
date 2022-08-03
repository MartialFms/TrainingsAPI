package fr.fms.apitrainings;

import fr.fms.apitrainings.dao.CategoryRepository;
import fr.fms.apitrainings.dao.TrainingRepository;
import fr.fms.apitrainings.entities.Category;
import fr.fms.apitrainings.entities.Role;
import fr.fms.apitrainings.entities.Training;
import fr.fms.apitrainings.entities.Users;
import fr.fms.apitrainings.security.SecurityConfig;
import fr.fms.apitrainings.service.ImplImageService;
import fr.fms.apitrainings.service.ImplRoleService;
import fr.fms.apitrainings.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ApiTrainingsApplication implements CommandLineRunner {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImplImageService implImageService;

    @Autowired
    private ImplRoleService implRoleService;

    @Autowired
    private ImplUserService implUserService;

    @Autowired
    private SecurityConfig security;


    public static void main(String[] args) {
        SpringApplication.run(ApiTrainingsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        populateTrainings();
        populateUsers();
    }

    public void populateTrainings() {
        Category prog = categoryRepository.save(new Category(null, "Programmation"));
        Category framework = categoryRepository.save(new Category(null, "Framework"));
        Category cms = categoryRepository.save(new Category(null, "CMS"));

        trainingRepository.save(new Training(null, "Java", "Formation Java SE 8 sur 5 jours", 1500, 1, "java.png", prog));
        trainingRepository.save(new Training(null, "DotNet", "Formation DotNet sur 3 jours", 1000, 1, "noimage.png", prog));
        trainingRepository.save(new Training(null, "Python", "Formation Python/Django sur 5 jours", 1500, 1, "python.png", prog));
        trainingRepository.save(new Training(null, "PHP Frameworks", "Symfony sur 15 jours", 2500, 1, "symfony.png", framework));
        trainingRepository.save(new Training(null, "Spring", "Spring Core/MVC/Security sur 20 jours", 5500, 1, "noimage.png", framework));
        trainingRepository.save(new Training(null, "WordPress", "Découverte WP 8", 1800, 1, "noimage.png", cms));
        trainingRepository.save(new Training(null, "Dupral", "Prise en main", 1000, 1, "noimage.png", cms));
    }

    public void populateUsers() {
        Role admin = implRoleService.save(new Role(null, "ADMIN"));
        Role user = implRoleService.save(new Role(null, "USER"));

//        String mdp = implUserService.encodePassword("123");

        String mdp = "$2a$12$ddGbM6lvl6AyWIAhFKF6PeLvkyN//GpC8Qoe1qe1bA12V4YOAL05q";

        List<Role> del = new ArrayList<>();
        del.add(admin);
        del.add(user);

        List<Role> mama = new ArrayList<>();
        mama.add(user);

        implUserService.save(new Users(null, "j.delmerie@live.fr", "del",  security.encodePassword("mdp1"), true, del));
        implUserService.save(new Users(null, "mama@live.fr", "mamacita", security.encodePassword("mdp2"),true, mama));
    }
}
