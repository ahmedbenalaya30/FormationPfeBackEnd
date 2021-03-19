package de.cynapsys.formation.service;

import java.util.List;

import de.cynapsys.formation.model.Commentaire;

public interface ICommentaireService {
	public void add(Commentaire commentaire);
	public void delete(Integer id);
	public void update(Integer id, Commentaire commentaire);
	public List<Commentaire> findALL();
	public Commentaire findById(Integer id );

}

