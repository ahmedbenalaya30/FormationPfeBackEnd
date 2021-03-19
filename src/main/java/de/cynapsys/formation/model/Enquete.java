package de.cynapsys.formation.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Enquete {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String titre;
	
	private LocalDate dateDebut;
	
	private LocalDate dateFin;
	
	private boolean visibilite=false;

	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	private List<Vote> votes;
	
	@ManyToMany(fetch = FetchType.LAZY)//cascade=CascadeType.DETACH
    @JoinTable(name = "employe_enquete",joinColumns = @JoinColumn(name = "id_enquete", referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "id_employe", referencedColumnName = "id"))
	private List<Utilisateur> employe;
	
	public boolean isVisibilite() {
		return visibilite;
	}

	public void setVisibilite(boolean visibilite) {
		this.visibilite = visibilite;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public List<Utilisateur> getEmploye() {
		return employe;
	}

	public void setEmploye(List<Utilisateur> employe) {
		this.employe = employe;
	}

	public Integer getId() {
		return id;
	}

}