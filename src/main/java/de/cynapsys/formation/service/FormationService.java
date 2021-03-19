package de.cynapsys.formation.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.cynapsys.formation.model.Feedback;
import de.cynapsys.formation.model.Formation;
import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.repositories.FeedbackRepository;
import de.cynapsys.formation.repositories.FormationRepository;
import de.cynapsys.formation.repositories.UtilisateurRepository;

@Service
@Transactional
public class FormationService implements IFormationService {

	@Autowired
	FormationRepository formationRepository ;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	FeedbackRepository feedbackRepository;

	@Override
	public void add(Formation formation){
		// TODO Auto-generated method stub
		formationRepository.save(formation);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		formationRepository.deleteById(id);
	}
	@Override
	public void update(Integer id, Formation formation) {
		// TODO Auto-generated method stub
		formationRepository.save(formation);
	}

	@Override
	public List<Formation> findALL() {
		// TODO Auto-generated method stub
		return 		formationRepository.findAllByOrderByIdAsc();	}

	@Override
	public Formation findById(Integer id) {
		// TODO Auto-generated method stub
		return 		formationRepository.getOne(id);
	}

	@Override
	public void update(Formation formation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void AjouterUser(Formation formation, List<Utilisateur> ch) {
		// TODO Auto-generated method stub
		formation.setInvites(ch);
		formationRepository.save(formation);
	}

	//	@Override
	//	public void AjouterNote(Formation formation, String ch )
	//	{
	//		Map<String, Double> a=formation.getNotes();
	//		a.put(ch, 0.0);
	//		formation.setNotes(a);
	//		formationRepository.save(formation);
	//	}

	@Override
	public void AjouterNote(Formation formation, String ch, double moy) {
		// TODO Auto-generated method stub

	}

	@Override
	public Formation ajouterFeedback(String ch , Integer id)
	{
		Formation f=formationRepository.getOne(id);
		//Map<String,Double> m=f.getNotes();
		List<Feedback> m=f.getFeedback();
		Feedback f1=new Feedback(ch,0.0);
		feedbackRepository.save(f1);
		m.add(f1);
		f.setFeedback(m);
		formationRepository.save(f);
		return f ; 
	}

	@Override
	public void ajouterInvite(Formation formation, List<Utilisateur> ch) {
		// TODO Auto-generated method stub
		formation.setInvites(ch);
		formationRepository.save(formation);	
	}
	@Override
	public void ajouterFeedbackeur(Formation formation, Integer idU) {
		// TODO Auto-generated method stub
		Utilisateur user=utilisateurRepository.getOne(idU);
		List<Utilisateur> f=formation.getFeedbackeurs();
		f.add(user);
		formation.setFeedbackeurs(f);
		formationRepository.save(formation);	
	}
	

	@Override
	public void AjouterNote(Formation formation, String ch) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean ComparerDate(LocalDate f, LocalDate d) {
		// TODO Auto-generated method stub
		if (f.isAfter(d))//f>d
			return true;
		else return false;
	}
	@Override
	public void DonnerNotes(Formation f , List<Integer> notes)
	{
		for (int i=0;i<f.getFeedback().size();i++)
		{
			int nb=f.getFeedback().get(i).getNb();
			double moy=f.getFeedback().get(i).getNote();
			if (nb==0)
				f.getFeedback().get(i).setNote(notes.get(i));
			else
				f.getFeedback().get(i).setNote((moy*nb+notes.get(i))/(nb+1));

			f.getFeedback().get(i).setNb(nb+1);

			feedbackRepository.save(f.getFeedback().get(i));
		}
		formationRepository.save(f);
	}

	@Override
	public boolean verifFeedbackeur(Formation formation,String username)
	{
		Optional<Utilisateur> u=utilisateurRepository.findByUsername(username);
		boolean verif=false;
		List<Utilisateur> Users=formation.getFeedbackeurs();
		for (int i = 0; i < Users.size(); i++) {
			Utilisateur user=Users.get(i);
			if (user.getId().equals(u.get().getId()))
				verif=true;
		}
		return verif;
	}

	@Override
	public boolean verifFeedbackeur(Formation formation, Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Formation> consulter(Integer id) {
		// TODO Auto-generated method stub
		List<Formation>formations=formationRepository.findAll();
		List<Formation>f=new ArrayList<Formation>();


		for (int i=0;i<formations.size();i++)
		{
			for (int j=0;j<formations.get(i).getInvites().size();j++)
			{
              if (formations.get(i).getInvites().get(j).getId()==id)
            	  f.add(formations.get(i));
			}

		}
		return f;
	}



}
