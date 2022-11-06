package com.example.test.service.impl;

import com.example.test.model.Configuracion;
import com.example.test.repository.IConfiguracionRepository;
import com.example.test.service.IConfiguracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracionServiceImpl implements IConfiguracionService {

    @Autowired
    private IConfiguracionRepository configuracionRepository;

    @Override
    public Configuracion findByKey(String key) {
        return configuracionRepository.findByLlave(key);
    }

    @Override
    public Configuracion saveOrUpdate(Configuracion config) {
        return configuracionRepository.save(config);
    }
}
