package com.anaclara.mongodb.relacionamentos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.anaclara.mongodb.relacionamentos.models.Perfil;
import com.anaclara.mongodb.relacionamentos.models.Postagem;
import com.anaclara.mongodb.relacionamentos.models.Usuario;
import com.anaclara.mongodb.relacionamentos.repository.PerfilRepository;
import com.anaclara.mongodb.relacionamentos.repository.PostagemRepository;
import com.anaclara.mongodb.relacionamentos.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PostagemRepository postagemRepository;
    
    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        if (usuario.getPerfil() != null && usuario.getPerfil().getId() == null) {
            Perfil perfilSalvo = perfilRepository.save(usuario.getPerfil());
            usuario.setPerfil(perfilSalvo);
            Postagem postagemSalvo = postagemRepository.save(usuario.getPostagens().get(0));
            usuario.getPostagens().set(0, postagemSalvo);

        }
        return usuarioRepository.save(usuario);
    }
    
    @GetMapping
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable("id") String id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable("id") String id, @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.findById(id).orElse(null);
        if(usuarioSalvo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        
        usuarioSalvo.setNome(usuario.getNome());

        if (usuario.getPerfil() != null) {
            usuarioSalvo.setPerfil(usuario.getPerfil());
        }

        if (usuario.getPostagens() != null) {
            usuarioSalvo.setPostagens(usuario.getPostagens());
        }

        return usuarioRepository.save(usuarioSalvo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        Usuario usuarioSalvo = usuarioRepository.findById(id).orElse(null);
        if (usuarioSalvo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        usuarioRepository.delete(usuarioSalvo);
    }
}
