package fr.fms.apitrainings;

import fr.fms.apitrainings.controller.TrainingController;
import fr.fms.apitrainings.entities.Category;
import fr.fms.apitrainings.entities.Training;
import fr.fms.apitrainings.service.ImplCategoryService;
import fr.fms.apitrainings.service.ImplTrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(controllers = TrainingController.class)

@SpringBootTest
@AutoConfigureMockMvc
public class TrainingControllerTest {
    @Autowired
    private MockMvc mock;

    ImplCategoryService implCategoryService;

    ImplTrainingService implTrainingService;

    @Test
    public void testGetTrainings() throws Exception {
        mock.perform(get("/api/trainings")).andExpect(status().isOk());
    }

    @Test
    public void testGetOneTraining() throws Exception {
        mock.perform(get("/api/training/{id}", 1)).andExpect(status().isOk());
    }

    @Test
    public void testSaveOneTraining() throws Exception {

//        mock.perform(post())

        Training training = new Training(null, "Wix", "Développez un site de A à Z avec Wix", 599, 1, "noimage.png", null);
        mock.perform(post("/api/trainings", training)).andExpect(status().isCreated());
    }
}
