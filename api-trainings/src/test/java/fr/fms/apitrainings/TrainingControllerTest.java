package fr.fms.apitrainings;

import fr.fms.apitrainings.controller.TrainingController;
import fr.fms.apitrainings.service.ImplTrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TrainingController.class)
public class TrainingControllerTest {
    @Autowired
    private MockMvc mock;

    @MockBean
    private ImplTrainingService implTrainingService;

    @Test
    public void testGetTrainings() throws Exception{
      mock.perform(get("/api/trainings")).andExpect(status().isOk());
    }

    @Test
    public void testGetOneTraining() throws Exception{
        mock.perform(get("/api/trainings/{id}", 1)).andExpect(status().isOk());
    }
}
