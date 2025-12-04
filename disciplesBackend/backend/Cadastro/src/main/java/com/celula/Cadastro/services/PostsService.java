package com.celula.Cadastro.services;

import com.celula.Cadastro.entities.Posts;
import com.celula.Cadastro.entities.Usuario;
import com.celula.Cadastro.repositories.PostsRepository;
import com.celula.Cadastro.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar postagem (já existente)
    public Posts criarPostagem(Posts post, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        post.setUsuario(usuario);

        return postsRepository.save(post);
    }

    // Listar todas postagens
    public List<Posts> listarPostagens() {
        return postsRepository.findAll();
    }

    // Buscar postagem por ID
    public Posts buscarPorId(Long id) {
        return postsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postagem não encontrada"));
    }

    // Deletar postagem (agora só se for dono)
    public void deletarPostagem(Long idPostagem, Long idUsuario) {
        Posts post = buscarPorId(idPostagem);

        if (post.getUsuario() == null || !post.getUsuario().getIdUsuario().equals(idUsuario)) {
            throw new RuntimeException("Você não tem permissão para deletar esta postagem.");
        }

        postsRepository.delete(post);
    }
}
