package com.docker.backend.Controller;

import com.docker.backend.CommandLiner.CommandLineAppStartupRunner;
import com.docker.backend.Dto.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/emp")
public class EmployeeController {
    private HashMap<Long, Employee> emploueeHashMap = new HashMap<>();
    private Long maxId=-99999L;

    public EmployeeController() {
        this.emploueeHashMap = CommandLineAppStartupRunner.employeeHashMap;
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<Object> getAllEmployee(){

        List<Employee> employees = new ArrayList<>(emploueeHashMap.values().stream().toList());

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<Object> getEmployeeById(@RequestParam("id") Long id){
        Employee employee = emploueeHashMap.get(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<Object> UpdateEmployee(@RequestBody Employee employee){
        if(emploueeHashMap.containsKey(employee.getId())){
            emploueeHashMap.remove(employee.getId(),employee);
            emploueeHashMap.put(employee.getId(),employee);
        }else
            addEmployee(employee);
        return new ResponseEntity<>(emploueeHashMap, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee){
        if(!emploueeHashMap.containsKey(employee.getId())){
            if(maxId>0){
                updateMaxId(maxId);
            }else{
                calculateMaxId();
            }
            employee.setId(maxId);
            emploueeHashMap.put(maxId,employee);
        }else{
            UpdateEmployee(employee);
        }
        return new ResponseEntity<>(emploueeHashMap, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<Object> deleteEmployee(@RequestParam("id") Long id){
        if (!emploueeHashMap.containsKey(id))
            return new ResponseEntity<>("No user exists",HttpStatus.OK);
        else {
            emploueeHashMap.remove(id);
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }


    }

    private void calculateMaxId(){
        for(Map.Entry<Long,Employee> a:emploueeHashMap.entrySet()){
            if(a.getKey().intValue()>maxId)
                maxId = a.getKey();
        }
        updateMaxId(maxId);
    }
    private void updateMaxId(Long id){
        maxId = maxId+1;
    }


}
