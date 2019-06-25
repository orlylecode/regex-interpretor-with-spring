package org.info.info303.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Valeur implements Serializable{
	@Id@ GeneratedValue
	private Long id ;
	private String valeur ;

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public Valeur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Valeur(String valeur) {
		super();
		this.valeur = valeur;
	}
	
	
}
