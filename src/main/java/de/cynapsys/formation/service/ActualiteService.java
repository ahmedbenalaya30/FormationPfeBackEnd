package de.cynapsys.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.cynapsys.formation.model.Actualite;
import de.cynapsys.formation.repositories.ActualiteRepository;

@Service
@Transactional
public class ActualiteService implements IActualiteService {
	@Autowired
	ActualiteRepository actualiteRepository ;

	@Override
	public void add(Actualite actualite) {
		// TODO Auto-generated method stub
		actualiteRepository.save(actualite);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		actualiteRepository.deleteById(id);
	}

	@Override
	public void update(Integer id, Actualite actualite) {
		// TODO Auto-generated method stub
		actualiteRepository.save(actualite);
	}

	@Override
	public List<Actualite> findALL() {
		// TODO Auto-generated method stub
		return 		actualiteRepository.findAll();	}

	@Override
	public Actualite findById(Integer id) {
		// TODO Auto-generated method stub
		return 		actualiteRepository.getOne(id);
	}

	@Override
	public void visible(Actualite actualite) {
		actualite.setVisibilite(true);
		actualiteRepository.save(actualite);
	}
	@Override
	public void invisible(Actualite actualite) {
		actualite.setVisibilite(false);
		actualiteRepository.save(actualite);
	}


}
