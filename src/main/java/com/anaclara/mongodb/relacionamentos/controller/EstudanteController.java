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

import com.anaclara.mongodb.relacionamentos.models.Estudante;
import com.anaclara.mongodb.relacionamentos.repository.EstudanteRepository;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {
    @Autowired
    private EstudanteRepository estudanteRepository;

    @GetMapping
    public List<Estudante> getAll() {
        return estudanteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Estudante> getById(@PathVariable("id") String id) {
        return estudanteRepository.findById(id);
    }

    @PostMapping
    public Estudante create(@RequestBody Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

    @PutMapping("/{id}")
    public Estudante update(@PathVariable("id") String id, @RequestBody Estudante estudante) {
        if (estudanteRepository.existsById(id)) {
            estudante.setId(id);
            return estudanteRepository.save(estudante);
        }
        throw new RuntimeException("Estudante com ID: " + id + " não encontrado");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        if (estudanteRepository.existsById(id)) {
            estudanteRepository.deleteById(id);
        } 
    }
}
