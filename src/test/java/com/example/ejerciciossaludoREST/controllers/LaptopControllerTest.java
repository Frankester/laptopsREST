package com.example.ejerciciossaludoREST.controllers;

import com.example.ejerciciossaludoREST.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    @Autowired
    RestTemplateBuilder restTemplateBuilder ;

    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+ port);

        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

        //reset data
        testRestTemplate.delete(URI.create("/api/laptops"));
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, response.getBody().length);
    }

    @Test
    void findOneById() {

        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);

        System.out.println(response.getBody());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void create() {

        HttpHeaders header = new HttpHeaders();

        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "model": "HP 27fw",
                    "price": 44998.0,
                    "avalible": true,
                    "screenSize": "1920 x 1080"
                }
                
                """;

        HttpEntity<String> entity = new HttpEntity<>(json,header);

        ResponseEntity<Laptop>  response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, entity,Laptop.class);

        Laptop result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals( "HP 27fw",result.getModel());
        assertEquals(1L, result.getId());
        assertEquals(true, result.getAvalible());
    }

    @Test
    void deleteAll(){

        HttpEntity<?> entity = HttpEntity.EMPTY;

        ResponseEntity<Void> response = testRestTemplate.exchange("/api/laptops", HttpMethod.DELETE,entity,Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(204, response.getStatusCodeValue());

    }

}