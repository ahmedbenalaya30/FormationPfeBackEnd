package de.cynapsys.formation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import de.cynapsys.formation.model.Choix;
import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.model.Vote;
import de.cynapsys.formation.repositories.ChoixRepository;
import de.cynapsys.formation.repositories.VoteRepository;

@Service
@Transactional
public  class VoteService implements IVoteService {
	@Autowired
	VoteRepository voteRepository ;

	@Autowired
	ChoixRepository choixRepository;

	@Autowired
	ChoixService choixService;

	@Override
	public void add(Vote vote) {
		// TODO Auto-generated method stub
		voteRepository.save(vote);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		voteRepository.deleteById(id);


	}

	@Override
	public void update( Vote vote) {
		// TODO Auto-generated method stub
		voteRepository.save(vote);
	}

	@Override
	public List<Vote> findALL(){
		// TODO Auto-generated method stub
		return 		voteRepository.findAllByOrderByIdAsc();	}
	@Override
	public Vote findById(Integer id){
		// TODO Auto-generated method stub
		return 		voteRepository.getOne(id);
	}
	@Override
	public Vote AjouterChoix(Vote vote, List<Choix> choix){
		// TODO Auto-generated method stub
		vote.setChoix(choix);
		voteRepository.save(vote);
		return vote;
	}
	@Override
	public List<Vote> findById(List<Integer> votes){
		// TODO Auto-generated method stub
		return voteRepository.findAllById(votes);
	}	
	@Override
	public Vote AjouterEmploye(Vote vote ,List<Utilisateur> users)
	{
		vote.setEmploye(users);
		voteRepository.save(vote);
		return vote;
	}

	@Override
	public Choix UpdateEtat(Choix choix){
		choix.setEtat("choisi");
		choixRepository.save(choix);
		return choix;
	}

	@Override
	public void visible(Vote vote)
	{
		vote.setVisibilite(true);
		voteRepository.save(vote);
	}
	@Override
	public void invisible(Vote vote)
	{
		vote.setVisibilite(false);
		voteRepository.save(vote);
	}

	@Override
	public void updateEtat(List<Vote> ch) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ch.size(); i++) {
			ch.get(i).setEtat("choisi");
			voteRepository.save(ch.get(i));
		}

	}
@Override
	public double Calcul(Choix ch,Integer id) {
		// TODO Auto-generated method stub
		Vote vote=voteRepository.getOne(id);
		Integer s=vote.getNbvotants();
		return (double)ch.getNbvotants()/s;
		
	}

@Override
public void EffacerVote_Choix(Integer id) {
	// TODO Auto-generated method stub
	
}

public void CalculChoix(Integer id) {
	// TODO Auto-generated method stub
	
}
@Override
public boolean verifier1(Vote vote , Integer id ){
	boolean verif=false;
	List<Utilisateur> Users=vote.getEmploye();
	for (int i = 0; i < Users.size(); i++) {
		Utilisateur user=Users.get(i);
		if (user.getId().equals(id))
			verif=true;
	}
	return verif;


}

@Override
public boolean verifier1(Vote vote , String ch){
	boolean verif=false;
	List<Utilisateur> Users=vote.getEmploye();
	for (int i = 0; i < Users.size(); i++) {
		Utilisateur user=Users.get(i);
		if (user.getUsername().equals(ch))
			verif=true;
	}
	return verif;


}

@Override
public Integer getVoteByIdchoix(Integer id_choix) {
	// TODO Auto-generated method stub
	List<Vote> v=voteRepository.findAll();
	  Vote p=new Vote();
	for (int i=0;i<v.size();i++)
	{
		List<Choix> Votechoix=v.get(i).getChoix();
	   for (int j=0;j<Votechoix.size();j++)
	   {
		   if (Votechoix.get(j).getId().equals(id_choix))
			   p=v.get(i);
	   }
	   
		   
	}
	return p.getId();
	
	
}
//@Override
//public void Resultats(Vote vote)
//{
//	List<Double> p=new ArrayList<Double>();
//	List<Choix> choix=vote.getChoix();
//	for (int i=0;i<choix.size();i++)
//	{
//		 double a=(double)choix.get(i).getNbvotants();
//		 double c=(double)(vote.getNbvotants());
//		 double b=a/c;
//		p.add(b)  ;
//	}
//	vote.setResultats(p);
//	voteRepository.save(vote);
//}

@Override
public List<Double> Resultats(Vote vote) {
	// TODO Auto-generated method stub
	List<Double> p=new ArrayList<Double>();
	List<Choix> choix=vote.getChoix();
	if (choix.size()>0)
	for (int i=0;i<choix.size();i++)
	{
		 double a=(double)choix.get(i).getNbvotants();
		 double c=(double)(vote.getNbvotants());
		 double b=a/c;
		p.add(b)  ;
}
	return p;
}
}




