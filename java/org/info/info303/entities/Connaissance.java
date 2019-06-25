package org.info.info303.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Connaissance  implements Serializable {
	@Id @GeneratedValue
	private Long id ;
	private  String description ;
	//@OneToMany(mappedBy = "connaissance")

 	@OneToMany
    @JoinColumn(name="connaissance")
	private List<Valeur> valeurs ;
	private Double densitee; 
	
    @ManyToOne
    @JoinColumn(name = "CODE_UTIL")
	private Utilisateur utilisateur;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public List<Valeur> getValeurs() {
		return valeurs;
	}

	public void setValeurs(List<Valeur> valeurs) {
		this.valeurs = valeurs;
	}

	public Double getDensitee() {
		return densitee;
	}

	public void setDensitee(Double densitee) {
		this.densitee = densitee;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public Connaissance(String description, List<Valeur> valeurs, Double densitee, Utilisateur utilisateur) {
		super();
		this.description = description;
		this.valeurs = valeurs;
		this.densitee = densitee;
		this.utilisateur = utilisateur;
	}

	public Connaissance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
