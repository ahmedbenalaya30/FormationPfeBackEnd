package de.cynapsys.formation.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Formation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String description;

	private LocalDate dateDebut;

	private LocalDate dateFin;
	
	private boolean visibilite=false;
	
    private  Integer nbPlace=30;
    
	private Integer nbParticipants=0;
	
	@ManyToMany( cascade = {CascadeType.REFRESH,CascadeType.PERSIST},fetch=FetchType.LAZY)
	private List<Utilisateur> invites; 


	@ManyToMany
	private List<Utilisateur> feedbackeurs; 

//	@ElementCollection
//	private Map<String,Double> notes;
	@OneToMany
	private List<Feedback> feedback;

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Utilisateur> getInvites() {
		return invites;
	}

	public void setInvites(List<Utilisateur> invites) {
		this.invites = invites;
	}

	public List<Utilisateur> getFeedbackeurs() {
		return feedbackeurs;
	}

	public void setFeedbackeurs(List<Utilisateur> feedbackeurs) {
		this.feedbackeurs = feedbackeurs;
	}

//	public Map<String, Double> getNotes() {
//		return notes;
//	}
//	public void setNotes(Map<String, Double> notes) {
//		this.notes = notes;
//	}
//	public List<Commentaire> getCommentaires() {
//		return commentaires;
//	}
	

	
	public boolean isVisibilite() {
		return visibilite;
	}

	public void setVisibilite(boolean visibilite) {
		this.visibilite = visibilite;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	public Integer getId() {
		return id;
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

	public Integer getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(Integer nbPlace) {
		this.nbPlace = nbPlace;
	}

	public Integer getNbParticipants() {
		return nbParticipants;
	}

	public void setNbParticipants(Integer nbParticipants) {
		this.nbParticipants = nbParticipants;
	}


	
	




}

