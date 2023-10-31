package controllers;

import controllers.AuthenticationController;
import exceptions.IncorrectPassword;
import exceptions.NotExistentUser;
import exceptions.UsernameAlreadyTaken;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import service.Baloot;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationControllerTest {
    private AuthenticationController authenticationController;

    @Mock
    private Baloot baloot;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationController = new AuthenticationController();
        authenticationController.setBaloot(baloot);
    }

    @Test
    public void testLoginSuccess() {
        // Arrange
        Map<String, String> input = new HashMap<>();
        input.put("username", "testUser");
        input.put("password", "testPassword");

        // Stub the behavior of Baloot
        Mockito.doNothing().when(baloot).login("testUser", "testPassword");

        // Act
        ResponseEntity<String> response = authenticationController.login(input);

        // Assert
        assertEquals("login successfully!", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLoginNotExistentUser() {
        // Arrange
        Map<String, String> input = new HashMap<>();
        input.put("username", "nonExistentUser");
        input.put("password", "testPassword");

        // Stub the behavior of Baloot to throw NotExistentUser
        Mockito.doThrow(NotExistentUser.class).when(baloot).login("nonExistentUser", "testPassword");

        // Act
        ResponseEntity<String> response = authenticationController.login(input);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testLoginIncorrectPassword() {
        // Arrange
        Map<String, String> input = new HashMap<>();
        input.put("username", "testUser");
        input.put("password", "incorrectPassword");

        // Stub the behavior of Baloot to throw IncorrectPassword
        Mockito.doThrow(IncorrectPassword.class).when(baloot).login("testUser", "incorrectPassword");

        // Act
        ResponseEntity<String> response = authenticationController.login(input);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testSignupSuccess() {
        // Arrange
        Map<String, String> input = new HashMap<>();
        input.put("address", "TestAddress");
        input.put("birthDate", "2000-01-01");
        input.put("email", "test@example.com");
        input.put("username", "newUser");
        input.put("password", "newPassword");

        // Create a new User object for the test
        User newUser = new User("newUser", "newPassword", "test@example.com", "2000-01-01", "TestAddress");

        // Stub the behavior of Baloot
        Mockito.doNothing().when(baloot).addUser(newUser);

        // Act
        ResponseEntity<String> response = authenticationController.signup(input);

        // Assert
        assertEquals("signup successfully!", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSignupUsernameAlreadyTaken() {
        // Arrange
        Map<String, String> input = new HashMap<>();
        input.put("address", "TestAddress");
        input.put("birthDate", "2000-01-01");
        input.put("email", "test@example.com");
        input.put("username", "existingUser");
        input.put("password", "newPassword");

        // Create a new User object with an existing username
        User newUser = new User("existingUser", "newPassword", "test@example.com", "2000-01-01", "TestAddress");

        // Stub the behavior of Baloot to throw UsernameAlreadyTaken
        Mockito.doThrow(UsernameAlreadyTaken.class).when(baloot).addUser(newUser);

        // Act
        ResponseEntity<String> response = authenticationController.signup(input);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
