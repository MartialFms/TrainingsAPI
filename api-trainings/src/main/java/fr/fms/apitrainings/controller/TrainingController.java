package fr.fms.apitrainings.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fms.apitrainings.entities.Training;
import fr.fms.apitrainings.errors.RecordNotFoundException;
import fr.fms.apitrainings.service.ImplCategoryService;
import fr.fms.apitrainings.service.ImplImageService;
import fr.fms.apitrainings.service.ImplTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TrainingController {

    @Autowired
    private ImplTrainingService implTrainingService;

    @Autowired
    private ImplCategoryService implCategoryService;

    @Autowired
    private ImplImageService implImageService;

    @GetMapping("/trainings")
    public List<Training> listOfTrainings() {
        return implTrainingService.getAll();
    }

    @PostMapping("/trainings")
    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Error> saveTraining(@RequestParam("image") MultipartFile image, @RequestParam("training") String trainingJson) throws JsonProcessingException {
        Training training = new ObjectMapper().readValue(trainingJson, Training.class);
        training.setCategory(implCategoryService.getOneById(training.getCategory().getId()).get());
//        String extention = implImageService.getFileExtention(image.getOriginalFilename());
//        String filename = training.getName() + "." + extention;
        if (training.getImage() != null) {
            training.setImage(image.getOriginalFilename());
        } else {
            training.setImage("noimage.png");
        }
        try {
            implImageService.save(image);
        } catch (Exception e) {
            String message = "Could not upload the file: " + image.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Error(message));
        }
        implTrainingService.save(training);
        return null;
    }

    @PutMapping("/training/{id}")
    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    public void updateTraining(@RequestParam("image") MultipartFile image, @RequestParam("training") String trainingJson) throws JsonProcessingException {
        Training training = new ObjectMapper().readValue(trainingJson, Training.class);
        training.setCategory(implCategoryService.getOneById(training.getCategory().getId()).get());
////        String extention = implImageService.getFileExtention(image.getOriginalFilename());
////        String filename = training.getName() + "." + extention;
        if (training.getImage() != null && training.getImage() != image.getOriginalFilename()) {
            training.setImage(image.getOriginalFilename());
        } else {
            training.setImage("noimage.png");
        }
        try {
            implImageService.save(image);
        } catch (Exception e) {
            String message = "Could not upload the file: " + image.getOriginalFilename() + "!";
        }
        implTrainingService.save(training);
    }

    @DeleteMapping("/trainings/{id}")
    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteTraining(@PathVariable("id") long id) {
        implTrainingService.delete(id);
    }

    @GetMapping("/training/{id}")
    @PostAuthorize("hasAuthority('ROLE_ADMIN')")
    public Training getTrainingById(@PathVariable("id") long id) {
        return implTrainingService.getOneById(id).orElseThrow(
                () -> new RecordNotFoundException("Id de formation " + id + " n'existe pas."));
      /*  if(training.isPresent()){
            return new ResponseEntity<>(training.get(), HttpStatus.OK);
        }
        return null;*/
    }

    @GetMapping("/categorie/{id}/trainings")
    public List<Training> getArticlesByCat(@PathVariable("id") long id) {
        return implTrainingService.getByCategory(id);
    }

    @GetMapping(path = "/trainingImage/{id}")
    public byte[] getTrainingImage(@PathVariable("id") Long id) throws Exception {
        Training training = implTrainingService.getOneById(id).get();
        return Files.readAllBytes(Paths.get("uploads").resolve(training.getImage()));
    }
}
