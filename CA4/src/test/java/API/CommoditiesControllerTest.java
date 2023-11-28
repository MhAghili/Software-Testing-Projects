package API;

import application.BalootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.CommoditiesController;
import exceptions.NotExistentCommodity;
import exceptions.NotExistentUser;
import model.Commodity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import service.Baloot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = BalootApplication.class)
@AutoConfigureMockMvc
class CommoditiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Baloot baloot;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetCommodities() throws Exception {

        mockMvc.perform(get("/commodities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetCommodity() throws Exception {

        mockMvc.perform(get("/commodities/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("2"));
    }


    @Test
    void testGetCommodityNotExist() throws Exception {

        mockMvc.perform(get("/commodities/123"))  // this is does not exist in DB
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }

    @Test
    void testRateCommodity() throws Exception {

        // Request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("rate", "5");
        requestBody.put("username", "amin");

        mockMvc.perform(post("/commodities/2/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().string("rate added successfully!"));
    }

@Test
    void testRateCommodity_CommodityNotExist() throws Exception {

        // Request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("rate", "4");
        requestBody.put("username", "amin");

        mockMvc.perform(post("/commodities/22/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new NotExistentCommodity().getMessage()));
    }


    @Test
    void testRateCommodity_WrongInput() throws Exception {

        // Request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("rate", "");
        requestBody.put("username", "33");

        mockMvc.perform(post("/commodities/2/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAddCommodityComment() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "ali");
        requestBody.put("comment", "besiar aali");
        mockMvc.perform(post("/commodities/2/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(content().string("comment added successfully!"));
    }

    @Test
    void testAddCommodityComment_UserNotExist() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "reza"); // not existed user
        requestBody.put("comment", "besiar aali");
        mockMvc.perform(post("/commodities/2/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isNotFound())
                .andExpect(content().string(new NotExistentUser().getMessage()));

    }
    @Test
    void testgetCommodityComment() throws Exception {

        mockMvc.perform(get("/commodities/2/comment"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());


    }

@Test
    void testsearchCommodities() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("searchOption", ""); // not existed user
        requestBody.put("searchValue", "");
        mockMvc.perform(post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testgetSuggestedCommodities() throws Exception {
        mockMvc.perform(get("/commodities/2/suggested"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetSuggestedCommodities_CommodityNotExist() throws Exception {
        mockMvc.perform(get("/commodities/33/suggested"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }







}
