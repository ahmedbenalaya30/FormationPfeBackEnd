package de.cynapsys.formation.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vote {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String question;
	
	private boolean visibilite=false;
	
	private String etat="dispo";
	
	private int nbvotants=0;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	private List<Choix> choix;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Utilisateur> employe;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Enquete   enquete;
	
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Integer getId() {
		return id;
	}
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Choix> getChoix() {
		return choix;
	}

	public void setChoix(List<Choix> choix) {
		this.choix = choix;
	}
	
	public List<Utilisateur> getEmploye() {
		return employe;
	}
	
	public void setEmploye(List<Utilisateur> employe) {
		this.employe = employe;
	}
	public boolean isVisibilite() {
		return visibilite;
	}
	
	public void setVisibilite(boolean visibilite) {
		this.visibilite = visibilite;
	}
	public int getNbvotants() {
		return nbvotants;
	}
	
	public void setNbvotants(int nbvotants) {
		this.nbvotants = nbvotants;
	}
	
	
	
	
}

