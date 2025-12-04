package com.celula.Cadastro.controllers;

import com.celula.Cadastro.entities.Posts;
import com.celula.Cadastro.services.PostsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/postagens")
public class PostsController {

    @Autowired
    private PostsService postsService;

    // Criar postagem com possível imagem (multipart/form-data)
    @PostMapping(value = "/usuario/{idUsuario}", consumes = {"multipart/form-data"})
    public Posts criarPostagemComImagem(
            @PathVariable Long idUsuario,
            @RequestPart("titulo") String titulo,
            @RequestPart("conteudo") String conteudo,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem
    ) throws IOException {

        Posts post = new Posts();
        post.setTitulo(titulo);
        post.setConteudo(conteudo);

        if (imagem != null && !imagem.isEmpty()) {
            String nome = UUID.randomUUID().toString() + "_" + imagem.getOriginalFilename();
            Path caminho = Paths.get("uploads").resolve(nome);
            Files.createDirectories(caminho.getParent());
            Files.copy(imagem.getInputStream(), caminho);
            // salvar caminho público relativo
            post.setImagemUrl("/uploads/" + nome);
        }

        return postsService.criarPostagem(post, idUsuario);
    }

    // Listar todos
    @GetMapping
    public List<Posts> listarPostagens() {
        return postsService.listarPostagens();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public Posts buscarPorId(@PathVariable Long id) {
        return postsService.buscarPorId(id);
    }

    // Deletar (exige id do usuário que solicita)
    @DeleteMapping("/{idPostagem}/usuario/{idUsuario}")
    public void deletarPostagem(@PathVariable Long idPostagem, @PathVariable Long idUsuario) {
        postsService.deletarPostagem(idPostagem, idUsuario);
    }
}
