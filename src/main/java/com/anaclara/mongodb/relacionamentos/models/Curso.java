package com.anaclara.mongodb.relacionamentos.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "cursos")
public class Curso {
    @Id
    private String id;
    private String nome;

    @DBRef
    private List<Estudante> estudantes;
}
