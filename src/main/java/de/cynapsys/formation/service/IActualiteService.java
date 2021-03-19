package de.cynapsys.formation.service;

import java.util.List;

import de.cynapsys.formation.model.Actualite;

public interface IActualiteService {
	public void add(Actualite actualite);
	public void delete(Integer id);
	public List<Actualite> findALL();
	public Actualite findById(Integer id );
	void update(Integer id, Actualite actualite);
	void visible(Actualite actualite);
	void invisible(Actualite actualite);
}
