package com.example.departamentservice.service;

import com.example.departamentservice.model.Departament;
import com.example.departamentservice.repository.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentService {
    @Autowired
    private DepartamentRepository departamentRepository;

    public Departament createDepartament(Departament departament){
        return departamentRepository.save(departament);
    }

    public Departament getDepartamentById(Integer id){
        Optional<Departament> departamentOptional = departamentRepository.findById(id);
        return departamentOptional.orElse(null);
    }

    public List<Departament> findAll(){
        return departamentRepository.findAll();
    }

    public Departament updateDepartament(Departament departament){
        return departamentRepository.save(departament);
    }

    public Departament deleteDepartament(Integer id) {
        Optional<Departament> departamentOptional = departamentRepository.findById(id);
        if (departamentOptional.isPresent()) {
            Departament departament = departamentOptional.get();
            departamentRepository.deleteById(id);
            return departament;
        }
        return null;
    }
}
