package com.meusgastos.meusgastos.domain.service;

import com.meusgastos.meusgastos.domain.repository.TituloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TituloService {

    @Autowired
    private TituloRepository tituloRepository;


}
