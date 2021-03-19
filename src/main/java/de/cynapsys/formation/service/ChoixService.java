package de.cynapsys.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.cynapsys.formation.model.Choix;
import de.cynapsys.formation.repositories.ChoixRepository;

@Service
@Transactional
public class ChoixService implements IChoixService {
	@Autowired
	ChoixRepository choixRepository ;

	@Override
	public void add(Choix choix) {
		// TODO Auto-generated method stub
		choixRepository.save(choix);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		choixRepository.deleteById(id);
	}

	@Override
	public List<Choix> findALL() {
		// TODO Auto-generated method stub
		return 		choixRepository.findAllByOrderByIdAsc();	}

	@Override
	public Choix findById(Integer id) {
		// TODO Auto-generated method stub
		return 		choixRepository.getOne(id);
	}

	public void update(Integer id, Choix choix) {
		// TODO Auto-generated method stub
		choixRepository.save(choix);
	}

	@Override
	public List<Choix> findById(List<Integer> id) {
		return choixRepository.findAllById(id);	
	}

	@Override
	public Choix UpdateEtat(Choix choix) {
		choix.setEtat("choisi");
		choixRepository.save(choix);
		return choix;
	}

	@Override
	public void updateEtat(List<Choix> ch)
	{
		for (int i = 0; i < ch.size(); i++) {
			ch.get(i).setEtat("choisi");
			choixRepository.save(ch.get(i));
		}
	}

	@Override
	public void validerChoix(Integer id) {
		// TODO Auto-generated method stub
		Choix ch=choixRepository.getOne(id);
		ch.setNbvotants(ch.getNbvotants()+1);
		choixRepository.save(ch);
	}

	

}




