package com.example.test.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "configuracion")
@Data
public class Configuracion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String llave;
	private String valor;
}