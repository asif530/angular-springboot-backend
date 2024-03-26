package com.docker.backend.Controller;

import com.docker.backend.CommandLiner.CommandLineAppStartupRunner;
import com.docker.backend.Dto.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class GenericController {
    private HashMap<Long, Employee> emploueeHashMap = new HashMap<>();

    public GenericController() {
        this.emploueeHashMap = CommandLineAppStartupRunner.employeeHashMap;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
