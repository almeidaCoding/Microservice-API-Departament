package com.example.employeeservice.controller;

import com.example.employeeservice.dto.ResponseDTO;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/batch")
    public List<Employee> saveEmployee(@RequestBody List<Employee> employees){
        List<Employee> createEmployees = new ArrayList<>();
        for(Employee employee : employees){
            createEmployees.add(employeeService.saveEmployee(employee));
        }
        return createEmployees;
    }

    @GetMapping("/{id}")
    public ResponseDTO getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<ResponseDTO> findAll(){
        return employeeService.findAll();
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable Integer id){
        return employeeService.deleteEmployee(id);
    }

}
