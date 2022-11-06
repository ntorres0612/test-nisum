package com.example.test.repository;

import com.example.test.model.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConfiguracionRepository extends JpaRepository<Configuracion, Integer> {
    Configuracion findByLlave(String key);
}
