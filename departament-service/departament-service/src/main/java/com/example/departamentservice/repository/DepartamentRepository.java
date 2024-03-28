package com.example.departamentservice.repository;

import com.example.departamentservice.model.Departament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentRepository extends JpaRepository<Departament, Integer> {}
