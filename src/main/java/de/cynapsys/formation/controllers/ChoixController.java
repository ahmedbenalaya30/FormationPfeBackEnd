package de.cynapsys.formation.controllers;

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
import de.cynapsys.formation.repositories.ChoixRepository;
import de.cynapsys.formation.service.IChoixService;

@RestController
@RequestMapping("/choix")
@CrossOrigin
public class ChoixController {
	@Autowired
	IChoixService choixService; 
	
	@Autowired
	ChoixRepository choixRepository;

	@PostMapping("/ajouter")
	public Choix ajouterChoix (@RequestBody Choix choix)
	{
		choixService.add(choix);
		return choix;
	}
	@GetMapping("/afficher")
	public List<Choix> findAll() {
		return choixService.findALL();
	}
	@GetMapping("/{id}")
	public Choix findById(@PathVariable Integer id) {
		return 		choixService.findById(id);
	}

	@DeleteMapping("/supprimer/{id}")
	public void supprimer(@PathVariable Integer id) {
	//Choix ch=choixService.findById(id);
//	if (ch.getEtat()!="dispo")
//		choixRepository.deleteVote_Choix(id);
	choixService.delete(id);
	}
	@PostMapping("/maj/{id}")
	public Choix update(@RequestBody Choix choix,@PathVariable Integer id ) {
		choixService.update(id,choix);
		return choix;		
	}
	@PostMapping("validerChoix/{id}")
	public void validerChoix(@PathVariable Integer id) {
		choixService.validerChoix(id);

	}




}
