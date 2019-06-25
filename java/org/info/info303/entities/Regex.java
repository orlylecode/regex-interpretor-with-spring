package org.info.info303.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Regex implements Serializable{
	
	@Id @GeneratedValue
	private Long id ;
	private int g ;
	private String chaine ;
	private String description ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChaine() {
		return chaine;
	}
	public void setChaine(String chaine) {
		this.chaine = chaine;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getG() {
		return g;
	}
	public void setG(int group) {
		this.g = group;
	}
	
public Regex(String chaine, String description, int g) {
		super();
		this.chaine = chaine;
		this.description = description;
		this.g = g;
	}

public Regex() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
