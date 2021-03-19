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

import de.cynapsys.formation.model.E;
import de.cynapsys.formation.model.Enquete;
import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.model.Vote;
import de.cynapsys.formation.service.IEnqueteService;
import de.cynapsys.formation.service.IUtilisateurService;
import de.cynapsys.formation.service.IVoteService;

@RestController
@RequestMapping("/enquete")
@CrossOrigin
public class EnqueteController{
	@Autowired
	IEnqueteService enqueteService ;

	@Autowired
	IUtilisateurService utilisateurService;

	@Autowired
	IVoteService voteService;

	@PostMapping("/ajouterEnquete")
	public void ajouter(@RequestBody E enquete)
	{
		Enquete e1=new Enquete();
		e1.setTitre(enquete.getTitre());
		e1.setDateDebut(enquete.getDateDebut());
		e1.setDateFin(enquete.getDateFin());
		List<Vote> liste=voteService.findById(enquete.getVotes());
		for(int i=0;i<liste.size();i++)
		{
			liste.get(i).setEtat("choisi");
		}
		e1.setVotes(liste);
		enqueteService.add(e1);
	}

	@GetMapping("/afficher")
	public List<Enquete> findAll(){
		return enqueteService.findALL();
	}

	@GetMapping("/{id}")
	public Enquete findById(@PathVariable Integer id){
		return enqueteService.findById(id);
	}

	@DeleteMapping("/supprimer/{id}")
	public void supprimer(@PathVariable Integer id) {
		enqueteService.delete(id);
	}

	@PostMapping("/maj/{id}")
	public Enquete update(@RequestBody E Enquete,@PathVariable Integer id ) {
		Enquete e=enqueteService.findById(id);
		List<Vote> liste=e.getVotes();
		List<Vote> votes=voteService.findById(Enquete.getVotes());
		e.setTitre(Enquete.getTitre());
		e.setDateDebut(Enquete.getDateDebut());
		e.setDateFin(Enquete.getDateFin());
		for (int i=0;i<votes.size();i++)
		{
			liste.add(votes.get(i));
			votes.get(i).setEtat("choisi");
		}
		e.setVotes(liste);
		enqueteService.add(e);
		return e;
	}

	@PostMapping("/ajouterEmploye/{idU}")
	public Enquete ajouter(@RequestBody Enquete enquete, @PathVariable Integer idU){
		Utilisateur user=utilisateurService.findById(idU);
		List<Utilisateur> ch = enquete.getEmploye();
		ch.add(user);
		enquete=enqueteService.AjouterEmploye(enquete, ch);
		return  enquete;

	}
	@PostMapping("/ajouterVoteById/{id}")
	public Enquete ajouterVoteById(@RequestBody List<Integer> vote ,@PathVariable Integer id)
	{
		Enquete enquete=enqueteService.findById(id);
		List<Vote> ch = enquete.getVotes();
		ch.addAll(voteService.findById(vote));
		voteService.updateEtat(ch);
		enqueteService.AjouterVote(enquete, ch);
		return enquete;
	}
	@PostMapping("/visible")
	public void visibile(@RequestBody Enquete enquete){
		enqueteService.visible(enquete);
	}
	@PostMapping("/invisible")
	public void invisibile(@RequestBody Enquete enquete){
		enqueteService.invisible(enquete);
	}


	@GetMapping("/getMesEnquetes/{username}")
	public List<Enquete> getEnquete(@PathVariable String username)
	{
		List<Enquete> ch=enqueteService.findALL();
		List<Enquete> p=new ArrayList<Enquete>()  ;

		for(int i=0;i<ch.size();i++)
		{
			if (enqueteService.verifier1(ch.get(i),username)==false)
				p.add(ch.get(i));

		}
		return p ;
	}

	@GetMapping("/NbEnquetes/{username}")
	public Integer NbEnquete(@PathVariable String username)
	{
		List<Enquete> ch=enqueteService.findALL();
		Integer a=0;
		for(int i=0;i<ch.size();i++)
		{
			if (ch.get(i).isVisibilite())
				if (enqueteService.verifier1(ch.get(i),username)==false)
					a++;

		}
		return a;
	}
	@PostMapping("/getVoteByIdchoix/{id}")
	public void getVoteByIdchoix(@PathVariable Integer id)
	{
		Integer idV=voteService.getVoteByIdchoix(id);
		Vote vote=voteService.findById(idV);
		vote.setNbvotants(vote.getNbvotants() + 1 );
		voteService.add(vote);
	}


}





