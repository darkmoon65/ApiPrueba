package com.cesars.api.services.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesars.api.services.models.MorthyModel;
import com.cesars.api.services.services.ConsultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ApiConsulta")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @PostMapping()
    public String morthyData(@RequestBody MorthyModel info) {
        return consultaService.getData(info);
    }
    
}
