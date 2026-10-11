package com.duoc.backend;

import com.duoc.backend.user.User;
import com.duoc.backend.user.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    userRepository.deleteAll();

    User user = new User();
    user.setUsername("prueba");
    user.setEmail("prueba@test.com");
    user.setPassword("123456");
    user.setEnabled(true);

    userRepository.save(user);
    }


    @Test
    void testLoginSuccess() {
        // Arrange
        String baseUrl = "http://localhost:" + port + "/login";
        User loginRequest = new User();
        loginRequest.setUsername("prueba");
        loginRequest.setPassword("123456");

        System.out.println("LoginRequest: " + loginRequest.getUsername() + " " + loginRequest.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(loginRequest, headers);
        System.out.println("Request: " + request.getBody());
        // Act
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);

        // Assert
        System.out.println("Response: " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Response: " + response.getBody());
        assertNotNull(response.getBody());
        System.out.println("Response: " + response.getBody().startsWith("Bearer "));
        assertTrue(response.getBody().startsWith("Bearer "));
    }

    @Test
    void testLoginInvalidPassword() {
        // Arrange
        String baseUrl = "http://localhost:" + port + "/login";
        User loginRequest = new User();
        loginRequest.setUsername("prueba");
        loginRequest.setPassword("1234567");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(loginRequest, headers);

        // Act
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
