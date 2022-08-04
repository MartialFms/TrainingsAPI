package fr.fms.apitrainings;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
        @Autowired
        private MockMvc mock;

        @Test
        public void testGetCategories() throws Exception{
            mock.perform(get("/api/categories")).andExpect(status().isOk());
        }

        @Test
        public void testGetOneCategory() throws Exception{
            mock.perform(get("/api/category/{id}", 1)).andExpect(status().isOk());
        }

        @Test
        public void testGetTrainingsByCategory() throws Exception{
            mock.perform(get("/api/categorie/{id}/trainings",1)).andExpect(status().isOk());
    }

}
