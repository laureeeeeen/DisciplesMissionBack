package com.celula.Cadastro.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeocodingService {
    private final RestTemplate restTemplate = new RestTemplate();

    public Coordenadas obterCoordenadas(String endereco) {
        try {
            String url = UriComponentsBuilder
                    .newInstance() // ✅ em vez de fromHttpUrl (não deprecated)
                    .scheme("https")
                    .host("nominatim.openstreetmap.org")
                    .path("/search")
                    .queryParam("q", endereco)
                    .queryParam("format", "json")
                    .queryParam("limit", "1")
                    .build()
                    .toUriString();

            // Jackson converte o JSON direto nesse array
            NominatimResponse[] response =
                    restTemplate.getForObject(url, NominatimResponse[].class);

            if (response == null || response.length == 0) {
                return null;
            }

            NominatimResponse primeiro = response[0];

            double lat = Double.parseDouble(primeiro.getLat());
            double lon = Double.parseDouble(primeiro.getLon());

            return new Coordenadas(lat, lon);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // se der erro, não quebra o fluxo
        }
    }
}
