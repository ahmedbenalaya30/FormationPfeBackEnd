package de.cynapsys.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.cynapsys.formation.model.Enquete;
import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.model.Vote;
import de.cynapsys.formation.repositories.EnqueteRepository;

@Service
@Transactional
public class EnqueteService implements IEnqueteService{
	@Autowired
	EnqueteRepository enqueteRepository ;

	@Override
	public void add(Enquete enquete) {
		// TODO Auto-generated method stub
		enqueteRepository.save(enquete);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		enqueteRepository.deleteById(id);
	}

	@Override
	public void update(Enquete enquete) {
		// TODO Auto-generated method stub
		enqueteRepository.save(enquete);
	}

	@Override
	public List<Enquete> findALL() {
		// TODO Auto-generated method stub
		return 		enqueteRepository.findAllByOrderByIdAsc();	}

	@Override
	public Enquete findById(Integer id) {
		// TODO Auto-generated method stub
		return 		enqueteRepository.getOne(id);
	}
	
	@Override
	public Enquete AjouterEmploye(Enquete enquete, List<Utilisateur> users) {
		// TODO Auto-generated method stub
		enquete.setEmploye(users);
		enqueteRepository.save(enquete);
		return enquete;
	}
	@Override
	public void update(Integer id, Enquete enquete) {
		// TODO Auto-generated method stub
		enqueteRepository.save(enquete);
	}
	@Override
	public void AjouterVote(Enquete enquete, List<Vote> ch) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			enquete.setVotes(ch);
			enqueteRepository.save(enquete);
		}
	@Override
	public void visible(Enquete enquete) {
		// TODO Auto-generated method stub
		enquete.setVisibilite(true);
		enqueteRepository.save(enquete);
	}

	@Override
	public void invisible(Enquete enquete) {
		// TODO Auto-generated method stub
		enquete.setVisibilite(false);
		enqueteRepository.save(enquete);
	}

	@Override
public boolean verifier1(Enquete enquete , String ch){
	boolean verif=false;
	List<Utilisateur> Users=enquete.getEmploye();
	for (int i = 0; i < Users.size(); i++) {
		Utilisateur user=Users.get(i);
		if (user.getUsername().equals(ch))
			verif=true;
	}
	return verif;


}
	}

