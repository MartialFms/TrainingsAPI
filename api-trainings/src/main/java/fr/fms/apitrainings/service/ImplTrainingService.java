package fr.fms.apitrainings.service;

import fr.fms.apitrainings.dao.TrainingRepository;
import fr.fms.apitrainings.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplTrainingService implements IService<Training> {

   @Autowired
    TrainingRepository trainingRepository;

    @Override
    public List<Training> getAll() {
        return trainingRepository.findAll();
    }

    @Override
    public Optional<Training> getOneById(long id) {
        return trainingRepository.findById(id);
    }

    @Override
    public Training save(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public void delete(long id) {
        trainingRepository.deleteById(id);
    }

    public List<Training> getByCategory(long id){
        return trainingRepository.findByCategoryId(id);
    }
}
