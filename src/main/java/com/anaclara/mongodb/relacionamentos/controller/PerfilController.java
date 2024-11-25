package com.anaclara.mongodb.relacionamentos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anaclara.mongodb.relacionamentos.models.Perfil;
import com.anaclara.mongodb.relacionamentos.repository.PerfilRepository;

@RestController
@RequestMapping("/perfis")
public class PerfilController {
    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public List<Perfil> getAll() {
        return perfilRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Perfil getById(@PathVariable("id") String id) {
        return perfilRepository.findById(id).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
    }

    @PostMapping
    public Perfil create(@RequestBody Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    @PutMapping("/{id}")    
    public Perfil update(@PathVariable("id") String id, @RequestBody Perfil perfil){
        Perfil perfilSalvo = perfilRepository.findById(id).orElse(null);
        if(perfilSalvo != null){
            perfilSalvo.setBio(perfil.getBio());
            perfilSalvo.setAvatarUrl(perfil.getAvatarUrl());
            return perfilRepository.save(perfilSalvo);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        perfilRepository.deleteById(id);
    }
}
