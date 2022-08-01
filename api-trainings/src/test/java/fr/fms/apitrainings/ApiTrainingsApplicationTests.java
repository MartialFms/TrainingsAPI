package fr.fms.apitrainings;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ApiTrainingsApplicationTests {

    @Autowired
    private MockMvc mock;

    @Test
    void testGetTrainingsAndTestName() throws Exception{
/*        mock.perform(get("/api/trainings"))
                .andExpect(jsonPath("$[0].name").isNotEmpty());*/
        mock.perform(get("/api/trainings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Java")));
    }

}
