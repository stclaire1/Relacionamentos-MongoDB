package com.anaclara.mongodb.relacionamentos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.anaclara.mongodb.relacionamentos.models.Postagem;

@Repository
public interface PostagemRepository extends MongoRepository<Postagem, String> {
    
}
