package controllers;

import exceptions.NotExistentComment;
import model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import service.Baloot;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentControllerTest {
    @InjectMocks
    private CommentController commentController;

    @Mock
    private Baloot baloot;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLikeCommentSuccess() throws NotExistentComment {
        // Arrange
        int commentId = 1;
        String username = "testUser";
        Comment comment = new Comment(commentId, "user@example.com", "testUser", 123, "Test Comment");

        Mockito.when(baloot.getCommentById(commentId)).thenReturn(comment);

        Map<String, String> input = new HashMap<>();
        input.put("username", username);

        ResponseEntity<String> response = commentController.likeComment(Integer.toString(commentId), input);

        assertEquals("The comment was successfully liked!", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLikeCommentNotExistentComment() throws NotExistentComment {
        // Arrange
        int commentId = 1;
        String username = "testUser";

        Mockito.when(baloot.getCommentById(commentId)).thenThrow(NotExistentComment.class);

        Map<String, String> input = new HashMap<>();
        input.put("username", username);

        ResponseEntity<String> response = commentController.likeComment(Integer.toString(commentId), input);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDislikeCommentSuccess() throws NotExistentComment {
        // Arrange
        int commentId = 1;
        String username = "testUser";
        Comment comment = new Comment(commentId, "user@example.com", "testUser", 123, "Test Comment");

        Mockito.when(baloot.getCommentById(commentId)).thenReturn(comment);

        Map<String, String> input = new HashMap<>();
        input.put("username", username);

        ResponseEntity<String> response = commentController.dislikeComment(Integer.toString(commentId), input);

        assertEquals("The comment was successfully disliked!", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDislikeCommentNotExistentComment() throws NotExistentComment {
        int commentId = 1;
        String username = "testUser";

        Mockito.when(baloot.getCommentById(commentId)).thenThrow(NotExistentComment.class);

        Map<String, String> input = new HashMap<>();
        input.put("username", username);

        ResponseEntity<String> response = commentController.dislikeComment(Integer.toString(commentId), input);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
