package de.cynapsys.formation.service;

import java.time.LocalDate;
import java.util.List;

import de.cynapsys.formation.model.Formation;
import de.cynapsys.formation.model.Utilisateur;

public interface IFormationService {
	public void add(Formation formation);
	public void delete(Integer id);
	public void update(Formation formation);
	public List<Formation> findALL();
	public Formation findById(Integer id );
	public void update(Integer id, Formation formation);
	public void AjouterUser(Formation formation, List<Utilisateur> ch);
	void AjouterNote(Formation formation, String ch, double moy);
	void AjouterNote(Formation formation, String ch);
	public Formation ajouterFeedback(String ch, Integer id);
	public void ajouterInvite(Formation formation, List<Utilisateur> ch);
	public  void ajouterFeedbackeur(Formation formation, Integer idU);
	public boolean ComparerDate(LocalDate d,LocalDate f);
	void DonnerNotes(Formation f, List<Integer> notes);
	boolean verifFeedbackeur(Formation formation, Integer id);
	boolean verifFeedbackeur(Formation formation, String username);
	public List<Formation> consulter(Integer id);
}
