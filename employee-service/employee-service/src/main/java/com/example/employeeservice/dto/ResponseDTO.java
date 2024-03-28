package com.example.employeeservice.dto;

import com.example.employeeservice.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private Employee employee;
    private DepartamentDTO departamentDTO;
}
