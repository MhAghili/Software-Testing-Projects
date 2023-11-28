package API;

import application.BalootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.InvalidCreditRange;
import exceptions.NotExistentCommodity;
import exceptions.NotExistentUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import service.Baloot;

import java.util.HashMap;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = BalootApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Baloot baloot;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void getUser() throws Exception {
        String userId = "ali";
        mockMvc.perform(get("/users/"+userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(userId));
    }

    @Test
    void getnotExistedUser() throws Exception {
        String userId = "reza";
        mockMvc.perform(get("/users/"+userId))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));

    }

    @Test
    void addCredit() throws Exception {
        String userId = "ali";
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", "400");
        mockMvc.perform(post("/users/"+ userId +"/credit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().string("credit added successfully!"));
    }
    @Test
    void addCredit_UserNotExist() throws Exception {
        String userId = "reza";
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", "400");
        mockMvc.perform(post("/users/"+ userId +"/credit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new NotExistentUser().getMessage()));
    }
    @Test
    void addCredit_InvalidRange() throws Exception {
        String userId = "ali";
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", "-100");
        mockMvc.perform(post("/users/"+ userId +"/credit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(new InvalidCreditRange().getMessage()));
    }
    @Test
    void addCredit_InvalidFormat() throws Exception {
        String userId = "ali";
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", "invalidFormat");
        mockMvc.perform(post("/users/"+ userId +"/credit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Please enter a valid number for the credit amount."));
    }




}
