package com.anaclara.mongodb.relacionamentos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anaclara.mongodb.relacionamentos.models.Curso;
import com.anaclara.mongodb.relacionamentos.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Curso> getById(@PathVariable String id) {
        return cursoRepository.findById(id);
    }

    @PostMapping
    public Curso create(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }

    @PutMapping("/{id}")
    public Curso update(@PathVariable("id") String id, @RequestBody Curso curso) {
        if (cursoRepository.existsById(id)) {
            curso.setId(id);
            return cursoRepository.save(curso);
        }
        throw new RuntimeException("Curso com ID: " + id + " não encontrado");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } 
    }
}
