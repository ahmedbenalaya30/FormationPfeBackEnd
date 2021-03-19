package de.cynapsys.formation.model;

import java.time.LocalDate;
import java.util.List;

public class E {
	private String titre;

	private LocalDate dateDebut;

	private LocalDate dateFin;
	private List<Integer> votes;
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
	public List<Integer> getVotes() {
		return votes;
	}
	public void setVotes(List<Integer> votes) {
		this.votes = votes;
	}
	
	

}
