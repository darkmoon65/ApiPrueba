package com.cesars.api.services.services;

import com.cesars.api.services.models.MorthyModel;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsultaService {
    public String getData(MorthyModel dataEnviada)
    {
        final String uri = "https://rickandmortyapi.com/api/"+dataEnviada.getName()+"/"+dataEnviada.getId();

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return result;
    }
}
