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


import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.service.IUtilisateurService;

@RequestMapping("/utilisateur")
@RestController
@CrossOrigin
public class UtilisateurController {

	@Autowired
	IUtilisateurService utilisateurService ; 

	@PostMapping("/ajouter")
	public Utilisateur  ajouterUtilisateur(@RequestBody Utilisateur user) {
		utilisateurService.add(user);
		return user;
	}

	@GetMapping("/afficher")
	public List<Utilisateur> findAll() {
		return utilisateurService.findALL();
	}

	@GetMapping("/{id}")
	public Utilisateur findById(@PathVariable Integer id)
	{
		return 		utilisateurService.findById(id);
	}

	@DeleteMapping("/supprimer/{id}")
	public void supprimer(@PathVariable Integer id) {
		Utilisateur u=utilisateurService.findById(id);
		u.setEtat(false);
		utilisateurService.add(u);
	}
	@PostMapping("/maj/{id}")
	public Utilisateur update(@RequestBody Utilisateur user,@PathVariable Integer id ) {
		utilisateurService.update(id,user);
		return user;		
	}
}