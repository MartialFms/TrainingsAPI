package fr.fms.apitrainings;
import fr.fms.apitrainings.entities.OrderItem;
import fr.fms.apitrainings.entities.Orders;
import fr.fms.apitrainings.entities.Training;
import fr.fms.apitrainings.security.payload.LoginRequest;
import fr.fms.apitrainings.service.ImplOrderService;
import fr.fms.apitrainings.service.ImplTrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mock;
    @MockBean
    private ImplOrderService orderService;
    @MockBean
    private ImplTrainingService trainingService;
    @Test
    public void testGetOrders() throws Exception{
//        LoginRequest loginRequest = new LoginRequest("j.delmerie@live.fr","mdp1");
//        mock.perform(post("/api/signin", loginRequest)).andExpect(status().isOk());
        mock.perform(get("/api/orders")).andExpect(status().isForbidden());
    }

    @Test
    public void testGetOrderItems() throws Exception{
        Training training = null;
        if(trainingService.getOneById(1).isPresent()) training = trainingService.getOneById(1).get();
        Orders order = orderService.save(new Orders(1L,new Date(),10,2,null,null));
        List<OrderItem> orderItems = null;
        orderItems.add(new OrderItem(1L,1,1,training,order));
        order.setOrderItems(orderItems);
        orderService.save(order);
        mock.perform(get("/api/orderItems/{orderId}", 1)).andExpect(status().isForbidden());
    }
}