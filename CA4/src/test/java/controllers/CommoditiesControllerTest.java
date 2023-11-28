package controllers;

import exceptions.NotExistentCommodity;
import exceptions.NotExistentUser;
import model.Commodity;
import model.Comment;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import service.Baloot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommoditiesControllerTest {
    @InjectMocks
    private CommoditiesController commoditiesController;

    @Mock
    private Baloot baloot;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        commoditiesController = new CommoditiesController();
        commoditiesController.setBaloot(baloot);
    }

    @Test
    public void testGetCommodities() {
        ArrayList<Commodity> commodities = new ArrayList<>();
        Mockito.when(baloot.getCommodities()).thenReturn(commodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getCommodities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commodities, response.getBody());
    }

    @Test
    public void testGetCommoditySuccess() throws NotExistentCommodity {

        String commodityId = "1";
        Commodity commodity = new Commodity();
        Mockito.when(baloot.getCommodityById(commodityId)).thenReturn(commodity);

        ResponseEntity<Commodity> response = commoditiesController.getCommodity(commodityId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commodity, response.getBody());
    }

    @Test
    public void testGetCommodityNotExistent() throws NotExistentCommodity {

        String commodityId = "1";
        Mockito.when(baloot.getCommodityById(commodityId)).thenThrow(NotExistentCommodity.class);

        ResponseEntity<Commodity> response = commoditiesController.getCommodity(commodityId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testRateCommoditySuccess() throws NotExistentCommodity {
        String commodityId = "1";
        String username = "testUser";
        int rate = 5;
        Map<String, String> input = new HashMap<>();
        input.put("rate", String.valueOf(rate));
        input.put("username", username);

        Commodity commodity = new Commodity();
        Mockito.when(baloot.getCommodityById(commodityId)).thenReturn(commodity);

        ResponseEntity<String> response = commoditiesController.rateCommodity(commodityId, input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("rate added successfully!", response.getBody());
    }

    @Test
    public void testRateCommodityNotExistentCommodity() throws NotExistentCommodity {
        String commodityId = "1";
        String username = "testUser";
        int rate = 5;
        Map<String, String> input = new HashMap<>();
        input.put("rate", String.valueOf(rate));
        input.put("username", username);

        Mockito.when(baloot.getCommodityById(commodityId)).thenThrow(NotExistentCommodity.class);

        ResponseEntity<String> response = commoditiesController.rateCommodity(commodityId, input);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testRateCommodityInvalidRate() throws NotExistentCommodity {
        // Arrange
        String commodityId = "1";
        String username = "testUser";
        String invalidRate = "invalid";
        Map<String, String> input = new HashMap<>();
        input.put("rate", invalidRate);
        input.put("username", username);

        Commodity commodity = new Commodity();
        Mockito.when(baloot.getCommodityById(commodityId)).thenReturn(commodity);


        ResponseEntity<String> response = commoditiesController.rateCommodity(commodityId, input);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testAddCommodityComment() throws NotExistentUser {
        String commodityId = "1";
        String username = "testUser";
        String comment = "This is a test comment";
        Map<String, String> input = new HashMap<>();
        input.put("username", username);
        input.put("comment", comment);


        User mockUser = new User();
        Mockito.when(baloot.getUserById(username)).thenReturn(mockUser);

        int commentId = 123;
        Mockito.when(baloot.generateCommentId()).thenReturn(commentId);

        Comment mockComment = new Comment(commentId, mockUser.getEmail(), mockUser.getUsername(), Integer.parseInt(commodityId), comment);

        Mockito.doNothing().when(baloot).addComment(mockComment);

        ResponseEntity<String> response = commoditiesController.addCommodityComment(commodityId, input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("comment added successfully!", response.getBody());
    }
    @Test
    public void testAddCommodityCommentNotExist() throws NotExistentUser {
        String commodityId = "1";
        String username = "notExistUser";
        String comment = "This is a test comment";
        Map<String, String> input = new HashMap<>();
        input.put("username", username);
        input.put("comment", comment);


        Mockito.when(baloot.getUserById(username)).thenThrow(NotExistentUser.class);

        ResponseEntity<String> response = commoditiesController.addCommodityComment(commodityId, input);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void testGetCommodityComment() {

        String commodityId = "1";
        ArrayList<Comment> comments = new ArrayList<>();
        Mockito.when(baloot.getCommentsForCommodity(Integer.parseInt(commodityId))).thenReturn(comments);


        ResponseEntity<ArrayList<Comment>> response = commoditiesController.getCommodityComment(commodityId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comments, response.getBody());
    }

    @Test
    public void testSearchCommoditiesByName() {

        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "name");
        input.put("searchValue", "Test");

        ArrayList<Commodity> commodities = new ArrayList<>();
        Mockito.when(baloot.filterCommoditiesByName("Test")).thenReturn(commodities);


        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commodities, response.getBody());
    }

    @Test
    public void testSearchCommoditiesByCategory() {

        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "category");
        input.put("searchValue", "Electronics");

        ArrayList<Commodity> commodities = new ArrayList<>();
        Mockito.when(baloot.filterCommoditiesByCategory("Electronics")).thenReturn(commodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commodities, response.getBody());
    }

    @Test
    public void testSearchCommoditiesByProviderName() {
        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "provider");
        input.put("searchValue", "TestProvider");

        ArrayList<Commodity> commodities = new ArrayList<>();
        Mockito.when(baloot.filterCommoditiesByProviderName("TestProvider")).thenReturn(commodities);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commodities, response.getBody());
    }

    @Test
    public void testSearchCommoditiesInvalidSearchOption() {
        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "invalid");
        input.put("searchValue", "Test");

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.searchCommodities(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new ArrayList<>(), response.getBody());
    }

    @Test
    public void testGetSuggestedCommoditiesSuccess() throws NotExistentCommodity {
        String commodityId = "1";
        Commodity commodity = new Commodity();
        ArrayList<Commodity> suggestedCommodities = new ArrayList<>();

        Mockito.when(baloot.getCommodityById(commodityId)).thenReturn(commodity);
        Mockito.when(baloot.suggestSimilarCommodities(commodity)).thenReturn(suggestedCommodities);


        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getSuggestedCommodities(commodityId);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(suggestedCommodities, response.getBody());
    }

    @Test
    public void testGetSuggestedCommoditiesNotExistentCommodity() throws NotExistentCommodity {

        String commodityId = "1";
        Mockito.when(baloot.getCommodityById(commodityId)).thenThrow(NotExistentCommodity.class);

        ResponseEntity<ArrayList<Commodity>> response = commoditiesController.getSuggestedCommodities(commodityId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
