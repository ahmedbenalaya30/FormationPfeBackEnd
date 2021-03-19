package de.cynapsys.formation.service;

import java.util.List;

import de.cynapsys.formation.model.Utilisateur;


public interface IUtilisateurService {
	public void add(Utilisateur user);
	public void delete(Integer id);
	public void update(Integer id, Utilisateur user);
	public List<Utilisateur> findALL();
	public Utilisateur findById(Integer id );
	public List<Utilisateur> findById(List<Integer> invites);
}
