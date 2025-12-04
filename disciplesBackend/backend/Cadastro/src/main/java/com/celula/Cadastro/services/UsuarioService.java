package com.celula.Cadastro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.celula.Cadastro.entities.Usuario;
import com.celula.Cadastro.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario saveUsuario (Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado")); 
	}
	
	public void deleteUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario autenticarUsuario(String email, String senha) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario != null && usuario.getSenha().equals(senha)) {
			return usuario;
		}
		
		return null;
	}
	
	public Usuario updateUsuarios(Long id, Usuario novoUsuario) {
		Optional<Usuario> usuariosOptional = usuarioRepository.findById(id);
		if(usuariosOptional.isPresent()) {
			Usuario usuarioExistente = usuariosOptional.get();
			usuarioExistente.setNomeUsuario(novoUsuario.getNomeUsuario());
			usuarioExistente.setIdade(novoUsuario.getIdade());
			usuarioExistente.setEmail(novoUsuario.getEmail());
			usuarioExistente.setUserName(novoUsuario.getUserName());
			usuarioExistente.setSenha(novoUsuario.getSenha());
			
	
				return usuarioRepository.save(usuarioExistente);
				
			}else {
				return null;
			}
	}
	
}