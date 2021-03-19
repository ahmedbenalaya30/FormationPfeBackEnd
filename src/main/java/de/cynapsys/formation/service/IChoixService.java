package de.cynapsys.formation.service;

import java.util.List;

import de.cynapsys.formation.model.Choix;

public interface IChoixService {
	public void add(Choix choix);
	public void update(Integer id, Choix choix);
	public List<Choix> findALL();
	public Choix findById(Integer id );
	public void delete(Integer id);
	public List<Choix> findById(List<Integer> id);
	public Choix UpdateEtat(Choix choix);
	public void  updateEtat(List<Choix> ch);
	public void validerChoix(Integer id);

}
