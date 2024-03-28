package com.example.departamentservice.controller;

import com.example.departamentservice.model.Departament;
import com.example.departamentservice.service.DepartamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/departaments")
public class DepartamentController {

    @Autowired
    private DepartamentService departamentService;

    @PostMapping("/batch")
    public List<Departament> createDepartaments(@RequestBody List<Departament> departaments) {
        List<Departament> createdDepartaments = new ArrayList<>();
        for (Departament departament : departaments) {
            createdDepartaments.add(departamentService.createDepartament(departament));
        }
        return createdDepartaments;
    }

    @GetMapping("/{id}")
    public Departament getDepartamentById(@PathVariable Integer id){
        return departamentService.getDepartamentById(id);
    }

    @GetMapping
    public List<Departament> findAll(){
        return departamentService.findAll();
    }

    @PutMapping
    public Departament updateDepartament(@RequestBody Departament departament){
        return departamentService.updateDepartament(departament);
    }

    @DeleteMapping("/{id}")
    public Departament deleteDepartament(@PathVariable Integer id){
        return departamentService.deleteDepartament(id);
    }
}
