package de.cynapsys.formation.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Choix {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private int  nbvotants=0 ;

	private String libelle ;
	
	private String etat="dispo" ; 
	
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},fetch=FetchType.LAZY)
	private Vote   vote;
	
	
	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	
	public int getNbvotants() {
		return nbvotants;
	}

	public void setNbvotants(int nbvotants) {
		this.nbvotants = nbvotants;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getId() {
		return id;
	}

	public Choix() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
