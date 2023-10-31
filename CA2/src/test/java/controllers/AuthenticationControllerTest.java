package controllers;


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
    public void testLoginSuccess() throws NotExistentUser, IncorrectPassword {

        Map<String, String> input = new HashMap<>();
        input.put("username", "testUser");
        input.put("password", "testPassword");

        Mockito.doNothing().when(baloot).login("testUser", "testPassword");

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals("login successfully!", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLoginNotExistentUser() throws NotExistentUser, IncorrectPassword {
        Map<String, String> input = new HashMap<>();
        input.put("username", "nonExistentUser");
        input.put("password", "testPassword");
        Mockito.doThrow(NotExistentUser.class).when(baloot).login("nonExistentUser", "testPassword");

        ResponseEntity<String> response = authenticationController.login(input);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testLoginIncorrectPassword() throws NotExistentUser, IncorrectPassword {

        Map<String, String> input = new HashMap<>();
        input.put("username", "testUser");
        input.put("password", "incorrectPassword");


        Mockito.doThrow(IncorrectPassword.class).when(baloot).login("testUser", "incorrectPassword");


        ResponseEntity<String> response = authenticationController.login(input);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testSignupSuccess() throws UsernameAlreadyTaken {

        Map<String, String> input = new HashMap<>();
        input.put("address", "TestAddress");
        input.put("birthDate", "2000-01-01");
        input.put("email", "test@example.com");
        input.put("username", "newUser");
        input.put("password", "newPassword");


        User newUser = new User("newUser", "newPassword", "test@example.com", "2000-01-01", "TestAddress");


        Mockito.doNothing().when(baloot).addUser(newUser);


        ResponseEntity<String> response = authenticationController.signup(input);

        assertEquals("signup successfully!", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSignupUsernameAlreadyTaken() throws UsernameAlreadyTaken {

        Map<String, String> input = new HashMap<>();
        input.put("address", "TestAddress");
        input.put("birthDate", "2000-01-01");
        input.put("email", "test@example.com");
        input.put("username", "existingUser");
        input.put("password", "newPassword");


        User newUser = new User("existingUser", "newPassword", "test@example.com", "2000-01-01", "TestAddress");


        Mockito.doThrow(UsernameAlreadyTaken.class).when(baloot).addUser(newUser);


        ResponseEntity<String> response = authenticationController.signup(input);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
