package com.celula.Cadastro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.celula.Cadastro.dto.ContatoLiderDTO;
import com.celula.Cadastro.entities.Celula;
import com.celula.Cadastro.entities.Usuario;
import com.celula.Cadastro.repositories.CelulaRepository;

@Service
public class CelulaService {

    @Autowired
    private CelulaRepository celulaRepository;

    @Autowired
    private GeocodingService geocodingService; 

    @Autowired
    private EmailService emailService;  

    public Celula saveCelula(Celula celula) {

        // Se tiver local e ainda não tiver coordenadas, tenta buscar
        if (celula.getLocal() != null && !celula.getLocal().isBlank()
                && (celula.getLatitude() == null || celula.getLongitude() == null)) {

            Coordenadas coords = geocodingService.obterCoordenadas(celula.getLocal());
            if (coords != null) {
                celula.setLatitude(coords.getLatitude());
                celula.setLongitude(coords.getLongitude());
            }
        }

        return celulaRepository.save(celula);
    }

    public List<Celula> getAllCelulas() {
        return celulaRepository.findAll();
    }

    public Celula getCelulaById(Long id) {
        return celulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Celula não encontrada!"));
    }

    public void deleteCelula(Long id) {
        celulaRepository.deleteById(id);
    }

    public void enviarContatoParaLider(Long idCelula, ContatoLiderDTO contato) {
        Celula celula = getCelulaById(idCelula); // já lança exceção se não achar

        Usuario lider = celula.getUsuario();
        if (lider == null || lider.getEmail() == null || lider.getEmail().isBlank()) {
            throw new RuntimeException("Líder sem e-mail cadastrado.");
        }

        emailService.enviarEmailContatoLider(
                lider.getEmail(),
                celula.getNomeCelula(),
                contato.getNomeInteressado(),
                contato.getEmailInteressado(),
                contato.getMensagem()
        );
    }
}
