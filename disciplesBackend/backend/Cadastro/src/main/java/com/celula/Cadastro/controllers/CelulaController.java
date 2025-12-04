package com.celula.Cadastro.controllers;

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

import com.celula.Cadastro.dto.ContatoLiderDTO;
import com.celula.Cadastro.entities.Celula;
import com.celula.Cadastro.entities.Usuario;
import com.celula.Cadastro.repositories.UsuarioRepository;
import com.celula.Cadastro.services.CelulaService;

@RestController
@RequestMapping("/celula")
public class CelulaController {

	@Autowired
	private CelulaService celulaService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/usuario/{idUsuario}")
	public Celula createCelula(@RequestBody Celula celula, @PathVariable Long idUsuario) {
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		celula.setUsuario(usuario);
		return celulaService.saveCelula(celula);
	}
	
	@GetMapping
	public List<Celula> getAllCelulas() {
		return celulaService.getAllCelulas();
	}
	
	@GetMapping("/{id}")
	public Celula getCelula(@PathVariable Long id) {
		return celulaService.getCelulaById(id);
	}
	
	@PutMapping
	public Celula editCelula(@RequestBody Celula celula) {
		return celulaService.saveCelula(celula);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCelula(@PathVariable Long id) {
		celulaService.deleteCelula(id);
	}

	@PostMapping("/{idCelula}/contato")
    public void contatoLider(
            @PathVariable Long idCelula,
            @RequestBody ContatoLiderDTO contato
    ) {
        celulaService.enviarContatoParaLider(idCelula, contato);
    }
}
