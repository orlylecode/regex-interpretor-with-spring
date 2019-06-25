package org.info.info303.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Utilisateur implements Serializable{
	
private String nom ;
@Id
private String  email;
private String pass ;

	@ManyToMany
    @JoinColumn(name="utilisateur")
private List<Regex> regex ;

	@OneToMany
    @JoinColumn(name="utilisateur")
private List<Donnees> donnees ;
 	@OneToMany
    @JoinColumn(name="utilisateur")
private List<Connaissance> connaissances;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public List<Regex> getRegex() {
		return regex;
	}
	public void setRegex(List<Regex> regex) {
		this.regex = regex;
	}
	public List<Donnees> getDonnees() {
		return donnees;
	}
	public void setDonnees(List<Donnees> donnees) {
		this.donnees = donnees;
	}
	public List<Connaissance> getConnaissances() {
		return connaissances;
	}
	public void setConnaissances(List<Connaissance> connaissances) {
		this.connaissances = connaissances;
	}
	public Utilisateur(String nom, String email, String pass, List<Regex> regex, List<Donnees> donnees,
			List<Connaissance> connaissances) {
		super();
		this.nom = nom;
		this.email = email;
		this.pass = pass;
		this.regex = regex;
		this.donnees = donnees;
		this.connaissances = connaissances;
	}
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}
 	
 	
 	
 }
