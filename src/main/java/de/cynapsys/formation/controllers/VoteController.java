
package de.cynapsys.formation.controllers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cynapsys.formation.model.Choix;
import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.model.Vote;
import de.cynapsys.formation.model.v;
import de.cynapsys.formation.service.IChoixService;
import de.cynapsys.formation.service.IUtilisateurService;
import de.cynapsys.formation.service.IVoteService;

@RestController
@RequestMapping("/vote")
@CrossOrigin
public class VoteController {

	@Autowired
	IVoteService voteService;

	@Autowired
	IChoixService choixService;

	@Autowired
	IUtilisateurService utilisateurService;

	@PostMapping("/add")
	public void add(@RequestBody v vote)
	{
		Vote v1=new Vote();
		v1.setQuestion(vote.getQuestion());
		List<Choix> liste = new ArrayList<Choix>();

		for (int i=0;i<vote.getChoix().size();i++)
		{
			Choix c=new Choix();
			c.setLibelle(vote.getChoix().get(i));
			c.setEtat("aa");
			c.setNbvotants(0);
			//choixService.add(c);
			liste.add(c);
			//v1.getChoix().add(c);
			//v1.setChoix(v1.getChoix());
		}
		v1.setChoix(liste);
		voteService.add(v1);
	}
	@GetMapping("/afficher")
	public List<Vote> findAll(){
		return voteService.findALL();
	}

	@GetMapping("/{id}")//chercher un vote
	public Vote findById(@PathVariable Integer id){
		return voteService.findById(id);
	}
	@DeleteMapping("/supprimer/{id}")
	public void supprimer(@PathVariable Integer id){
		voteService.delete(id);
	}
	@PostMapping("/maj/{id}")
	public Vote update(@RequestBody v vote,@PathVariable Integer id ) {
		Vote v=voteService.findById(id);
		List<Choix> liste=v.getChoix();
		v.setQuestion(vote.getQuestion());
		for ( int i=0;i<vote.getChoix().size();i++)
		{
			Choix ch=new Choix();
			ch.setLibelle(vote.getChoix().get(i));
			liste.add(ch);
		}
		v.setChoix(liste);
		voteService.add(v);
		return v;
	}

	@PostMapping("/ajouterChoixById/{id}")
	public Vote ajouterChoixById(@RequestBody List<Integer> choix ,@PathVariable Integer id)
	{
		Vote vote=voteService.findById(id);
		List<Choix> ch = vote.getChoix();
		//		for (int i = 0; i < choix.size(); i++) {
		//			Choix choix1=choixService.findById(choix.get(i));
		//			ch.add(choix1);
		//		}
		ch.addAll(choixService.findById(choix));
		choixService.updateEtat(ch);
		voteService.AjouterChoix(vote, ch);
		return vote;
	}

	@PostMapping("/afficherVotes")
	public List<Vote> afficherVotes(@RequestBody List<Integer> votes ){
		return voteService.findById(votes);
	}

	@PostMapping("/ajouterEmploye/{idU}")
	public void ajouterUser(@RequestBody Vote vote, @PathVariable Integer idU){
		Utilisateur user=utilisateurService.findById(idU);
		List<Utilisateur> ch = vote.getEmploye();
		if (ch.contains(user)==false) {
			ch.add(user);
			voteService.AjouterEmploye(vote, ch);
			vote.setNbvotants(vote.getNbvotants() + 1);
			voteService.add(vote);
		}
	}


	@PostMapping("/calculChoix/{id}")
	public void calculChoix(@RequestBody Vote vote, @PathVariable Integer id ){

		Choix ch=choixService.findById(id);
		ch.setNbvotants(ch.getNbvotants() + 1);
		choixService.add(ch);		
	}
	@PostMapping("/calculChoixEnquete/{id}")
	public void calculChoixEnquete( @PathVariable Integer id ){
		Choix ch=choixService.findById(id);
		ch.setNbvotants(ch.getNbvotants() + 1);
		choixService.add(ch);		
	}
	//	@GetMapping("/GetMyvote/{id}")
	//    public List<Vote> obtenirVote(@PathVariable Integer id){
	//		List<Vote> p=new ArrayList<Vote>()  ;
	//		List<Vote> ch=voteService.findALL();
	//		boolean verif=false;
	//		int a=0;
	//		for (int i=0;i<ch.size();i++)
	//		{
	//			Vote v=ch.get(i);
	//			List<Utilisateur> Users=v.getEmploye();
	//			for (int j = 0; j < Users.size(); j++) {
	//				Utilisateur user=Users.get(j);
	//				if ((user.getId().equals(id)))
	//			 verif=true;
	//			}
	//			if (!verif)
	//			{
	//				p.add(v);
	//			}
	//			verif=false;
	//		}
	//		return p;
	//	}

	//	@GetMapping("/getMesvotes/{id}")
	//	public List<Vote> getVote(@PathVariable Integer id)
	//	{
	//	List<Vote> ch=voteService.findALL();
	//	List<Vote> p=new ArrayList<Vote>()  ;
	//
	//	for(int i=0;i<ch.size();i++)
	//	{
	//		if (voteService.verifier1(ch.get(i),id)==false)
	//			p.add(ch.get(i));
	//			
	//	}
	//return p ;
	//	}
	@GetMapping("/getMesvotes/{username}")
	public List<Vote> getVote(@PathVariable String username)
	{
		List<Vote> ch=voteService.findALL();
		List<Vote> p=new ArrayList<Vote>()  ;

		for(int i=0;i<ch.size();i++)
		{
			if (ch.get(i).getChoix().isEmpty()==false)
				if (voteService.verifier1(ch.get(i),username)==false)
					p.add(ch.get(i));
		}
		return p ;
	}


	@GetMapping("/Dejavotes/{username}")
	public List<Vote> DejaVote(@PathVariable String username)
	{
		List<Vote> ch=voteService.findALL();
		List<Vote> p=new ArrayList<Vote>()  ;

		for(int i=0;i<ch.size();i++)
		{
			if (ch.get(i).getChoix().isEmpty()==false)
				if (ch.get(i).isVisibilite())
					if (voteService.verifier1(ch.get(i),username)==true)
						p.add(ch.get(i));		
		}
		return p ;
	}


	@GetMapping("/Nbvotes/{username}")
	public Integer nbVote(@PathVariable String username)
	{
		List<Vote> ch=voteService.findALL();
		Integer s=0;
		for(int i=0;i<ch.size();i++)
		{
			if (ch.get(i).getChoix().isEmpty()==false)
				if (ch.get(i).isVisibilite()==true)
					if (voteService.verifier1(ch.get(i),username)==false)
						s++;

		}
		return s ;
	}

	//		@PostMapping("/Resultats/{idv}")
	//	public void calcul(@PathVariable Integer idv) {
	//			Vote vote=voteService.findById(idv);
	//		 voteService.Resultats(vote);
	//	}
	@PostMapping("/Resultats/{idv}")
	public List<Double> calcul(@PathVariable Integer idv) {
		Vote vote=voteService.findById(idv);
		return voteService.Resultats(vote);
	}
	@PostMapping("/etat/{id}")
	public Choix UpdateEtat(@RequestBody Choix choix,@PathVariable Integer id  ){
		return voteService.UpdateEtat(choix);
	}

	@PostMapping("/visible")
	public void visibile(@RequestBody Vote vote){
		voteService.visible(vote);
	}
	@PostMapping("/invisible")
	public void invisibile(@RequestBody Vote vote){
		voteService.invisible(vote);
	}

	@PostMapping("/verifier/{id}")
	public boolean verifier(@RequestBody Vote vote ,@PathVariable Integer id ){
		boolean verif=false;
		List<Utilisateur> Users=vote.getEmploye();
		for (int i = 0; i < Users.size(); i++) {
			Utilisateur user=Users.get(i);
			if (user.getId().equals(id))
				verif=true;
		}
		return verif;
	}


	@GetMapping("/voteDispo")
	public List<Vote> VoteDispo()
	{
		List<Vote> liste=voteService.findALL();
		List<Vote> v=new ArrayList<Vote>();
		for (int i=0;i<liste.size();i++)
		{
			if (liste.get(i).getEtat().equals("dispo"))
			v.add(liste.get(i));
		}
		return v;

	}


}






