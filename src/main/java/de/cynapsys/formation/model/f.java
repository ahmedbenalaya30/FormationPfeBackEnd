package de.cynapsys.formation.model;

import java.time.LocalDate;
import java.util.List;

public class f {
  
	private String description;

	private LocalDate dateDebut;

	private LocalDate dateFin;
	
	private List<Integer> invites;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public List<Integer> getInvites() {
		return invites;
	}
	public void setInvites(List<Integer> invites) {
		this.invites = invites;
	}

	
	
	
}
