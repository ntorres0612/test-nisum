package com.example.test.service;

import com.example.test.model.Configuracion;

public interface IConfiguracionService {
	Configuracion findByKey(String key);
	Configuracion saveOrUpdate(Configuracion config);
}
