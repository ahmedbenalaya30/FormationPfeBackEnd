package de.cynapsys.formation.service;

	import java.util.List;

import de.cynapsys.formation.model.Choix;
import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.model.Vote;


	public interface IVoteService {
		public void add(Vote user);
		public void delete(Integer id);
		public List<Vote> findALL();
		public Vote findById(Integer id );
		public Vote AjouterChoix(Vote vote, List<Choix> choix);
		public List<Vote> findById(List<Integer> votes);
		public Vote AjouterEmploye(Vote vote, List<Utilisateur> user);
		public Choix UpdateEtat(Choix choix);
		void visible(Vote vote);
		void invisible(Vote vote);
		double Calcul(Choix ch, Integer id);
		void EffacerVote_Choix(Integer id);
		boolean verifier1(Vote vote, Integer id);
		boolean verifier1(Vote vote, String ch);
		Integer getVoteByIdchoix(Integer id_choix);
		void update(Vote vote);
		public List<Double> Resultats(Vote vote);
		public void updateEtat(List<Vote> ch);
	}


