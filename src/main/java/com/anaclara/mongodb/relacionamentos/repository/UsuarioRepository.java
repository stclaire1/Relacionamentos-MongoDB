package com.anaclara.mongodb.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anaclara.mongodb.relacionamentos.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
}
