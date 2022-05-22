package com.example.ejerciciossaludoREST.controllers;

import com.example.ejerciciossaludoREST.entities.Laptop;
import com.example.ejerciciossaludoREST.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    @Value("{app.message}")
    String message;

    LaptopRepository repository;
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    public LaptopController(LaptopRepository repository){
        this.repository = repository;
    }

    @GetMapping("/api/laptops")
    @ApiOperation("get all Laptops")
    public List<Laptop> findAll(){
        log.info(message);
        return repository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    @ApiOperation("find Laptop by id")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        log.info(message);
        Optional<Laptop> laptopFound = repository.findById(id);

        if(laptopFound.isPresent()){
            return ResponseEntity.ok(laptopFound.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/api/laptops")
    @ApiOperation("create a Laptop")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
        log.info(message);
        Laptop result = repository.save(laptop);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/laptops")
    @ApiOperation("update a Laptop")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        log.info(message);
        if( laptop.getId() == null ){
            log.error(" cannot update a Laptop with null id");
            return ResponseEntity.badRequest().build();
        }
        if(!repository.existsById(laptop.getId())){
            log.error("Laptop not found");
            return ResponseEntity.notFound().build();
        }

        Laptop result = repository.save(laptop);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/laptops/{id}")
    @ApiOperation("delete a Laptop by id")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        log.info(message);
        if(!repository.existsById(id)){
            log.error(" cannot delete a Laptop with id null");
            return ResponseEntity.badRequest().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/laptops")
    @ApiOperation("delete all laptops")
    public ResponseEntity<Laptop> deleteAll(){
        log.info(message);
        repository.deleteAll();

        return ResponseEntity.noContent().build();
    }
}
