package com.spring.basics.springbasics.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(EmployeeController.API_PATH)
public class EmployeeController implements IController{

    public static final String API_PATH = "/employee";

    @Autowired
    private EmployeeService service;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable String id){
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Employee employee){
        String employeeId = service.save(employee).getId();
        final URI userPath = URI.create(API_PATH + "/" + employeeId);
        return ResponseEntity.ok().body(userPath);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Employee employee){
        String employeeId = service.update(employee).getId();
        final URI userPath = URI.create(API_PATH + "/" + employeeId);
        return ResponseEntity.ok().body(userPath);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity delete(@PathVariable String id){

        return ResponseEntity.ok().body(service.delete(id));
    }

    @Override
    public String getSomeName() {
        return somename;
    }
}
