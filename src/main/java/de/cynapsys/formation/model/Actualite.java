package de.cynapsys.formation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Actualite {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String message;
	
	private boolean visibilite=false;

	public boolean isVisibilite() {
		return visibilite;
	}

	public void setVisibilite(boolean visibilite) {
		this.visibilite = visibilite;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

}
