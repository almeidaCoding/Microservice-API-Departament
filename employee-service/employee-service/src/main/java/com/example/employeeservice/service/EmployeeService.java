package com.example.employeeservice.service;

import com.example.employeeservice.dto.DepartamentDTO;
import com.example.employeeservice.dto.ResponseDTO;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public ResponseDTO getEmployeeById(Integer id){
        ResponseDTO responseDTO = new ResponseDTO();
        Employee employee = new Employee();
        employee = employeeRepository.findById(id).get();

        ResponseEntity<DepartamentDTO> responseEntity =
                restTemplate.getForEntity(
                        "http://localhost:8080/departaments/"+employee.getDepartamentId(),
                        DepartamentDTO.class
                );
        DepartamentDTO departamentDTO = responseEntity.getBody();

        responseDTO.setEmployee(employee);
        responseDTO.setDepartamentDTO(departamentDTO);

        return responseDTO;
    }

    public List<ResponseDTO> findAll(){
        List<ResponseDTO> responseDTOs = new ArrayList<>();

        // Obtener la lista de empleados desde el repositorio
        List<Employee> employees = employeeRepository.findAll();

        // Obtener la lista de departamentos utilizando restTemplate
        ResponseEntity<DepartamentDTO[]> responseEntity =
                restTemplate.getForEntity(
                        "http://localhost:8080/departaments",
                        DepartamentDTO[].class
                );
        DepartamentDTO[] departamentDTOs = responseEntity.getBody();

        // Por cada empleado, crear un ResponseDTO y asignar el departamento correspondiente
        for (Employee employee : employees) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setEmployee(employee);
            assert departamentDTOs != null;
            responseDTO.setDepartamentDTO(findDepartamentById(Integer.valueOf(employee.getDepartamentId()), departamentDTOs));
            responseDTOs.add(responseDTO);
        }

        return responseDTOs;
    }

    private DepartamentDTO findDepartamentById(Integer departamentId, DepartamentDTO[] departamentDTOs) {
        for (DepartamentDTO departamentDTO : departamentDTOs) {
            if (departamentDTO.getId().equals(departamentId)) {
                return departamentDTO;
            }
        }
        return null;
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee deleteEmployee(Integer id) {
        Optional<Employee> departamentOptional = employeeRepository.findById(id);
        if (departamentOptional.isPresent()) {
            Employee employee = departamentOptional.get();
            employeeRepository.deleteById(id);
            return employee;
        }
        return null;
    }
}
