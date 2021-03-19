package de.cynapsys.formation.service;

import java.util.List;

import de.cynapsys.formation.model.Enquete;
import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.model.Vote;
	
	public interface IEnqueteService {
		public void add(Enquete enquete);
		public void delete(Integer id);
		public void update(Enquete enquete);
		public List<Enquete> findALL();
		public Enquete findById(Integer id );
		Enquete AjouterEmploye(Enquete enquete, List<Utilisateur> users);
		boolean verifier1(Enquete enquete, String ch);
		public void AjouterVote(Enquete enquete, List<Vote> ch);
		public void invisible(Enquete enquete);
		public void visible(Enquete enquete);
		void update(Integer id, Enquete enquete);

}
