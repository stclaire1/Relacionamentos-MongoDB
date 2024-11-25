package com.anaclara.mongodb.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anaclara.mongodb.relacionamentos.models.Perfil;

public interface PerfilRepository extends MongoRepository<Perfil, String> {
    
}
