package de.cynapsys.formation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.repositories.UtilisateurRepository;
@Service
@Transactional
public class UtilisateurService implements IUtilisateurService {
	@Autowired
	UtilisateurRepository utilisateurRepository ;

	@Override
	public void add(Utilisateur user) {
		// TODO Auto-generated method stub
		utilisateurRepository.save(user);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		utilisateurRepository.deleteById(id);


	}

	@Override
	public void update(Integer id , Utilisateur user) {
		// TODO Auto-generated method stub
		utilisateurRepository.save(user);


	}

	@Override
	public List<Utilisateur> findALL() {
		// TODO Auto-generated method stub
		List<Utilisateur> liste=utilisateurRepository.findAll();
		List<Utilisateur> u=new ArrayList<Utilisateur>();
		for (int i=0;i<liste.size();i++)
		{
			if (liste.get(i).getEtat()==true)
				u.add(liste.get(i));
		}
		return u;

	}

	@Override
	public Utilisateur findById(Integer id) {
		// TODO Auto-generated method stub
		return 		utilisateurRepository.getOne(id);
	}

	@Override
	public  List<Utilisateur> findById(List<Integer> invites) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findAllById(invites);
	}

}
