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

import com.anaclara.mongodb.relacionamentos.models.Postagem;
import com.anaclara.mongodb.relacionamentos.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
public class PostagemController {
    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping
    public List<Postagem> getAll() {
        return postagemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Postagem> getById(@PathVariable String id) {
        return postagemRepository.findById(id);
    }

    @PostMapping
    public Postagem create(@RequestBody Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    @PutMapping("/{id}")
    public Postagem update(@PathVariable String id, @RequestBody Postagem postagem) {
        if (postagemRepository.existsById(id)) {
            postagem.setId(id);
            return postagemRepository.save(postagem);
        }
        throw new RuntimeException("Postagem com ID: " + id + " não encontrada");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        if (postagemRepository.existsById(id)) {
            postagemRepository.deleteById(id);
        } 
    }
}
