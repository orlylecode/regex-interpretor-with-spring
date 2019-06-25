package org.info.info303.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Donnees implements Serializable{
	@Id @GeneratedValue
	private Long id ;
	private String data ;
	private Date date ;
    @ManyToOne
    @JoinColumn(name = "CODE_UTIL")
	private Utilisateur utilisateur ;
    
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Donnees(String data, Date date, Utilisateur utilisateur) {
		super();
		this.data = data;
		this.date = date;
		this.utilisateur = utilisateur;
	}
	public Donnees() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
