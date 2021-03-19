package de.cynapsys.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.cynapsys.formation.model.Commentaire;
import de.cynapsys.formation.repositories.CommentaireRepository;
@Service
@Transactional
public class CommentaireService implements ICommentaireService {
	@Autowired
	CommentaireRepository commentaireRepository ;

	@Override
	public void add(Commentaire commentaire) {
		// TODO Auto-generated method stub
		commentaireRepository.save(commentaire);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		commentaireRepository.deleteById(id);
	}

	@Override
	public void update(Integer id,Commentaire commentaire) {
		// TODO Auto-generated method stub
		commentaireRepository.save(commentaire);
	}

	@Override
	public List<Commentaire> findALL() {
		// TODO Auto-generated method stub
		return 		commentaireRepository.findAllByOrderByDateAsc();	}

	@Override
	public Commentaire findById(Integer id) {
		// TODO Auto-generated method stub
		return 		commentaireRepository.getOne(id);
	}


}