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

import de.cynapsys.formation.model.Commentaire;
import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.service.ICommentaireService;
import de.cynapsys.formation.service.IUtilisateurService;

@RestController
@RequestMapping("/commentaire")
@CrossOrigin
public class CommentaireController {
	@Autowired
	ICommentaireService  commentaireService;

	@Autowired 
	IUtilisateurService utilisateurService;

	@PostMapping("/ajouter/{id}")
	public Commentaire ajouterCommentaire (@RequestBody Commentaire commentaire,@PathVariable Integer id){
		Utilisateur user=utilisateurService.findById(id);
		commentaire.setUtilisateur(user);
		commentaireService.add(commentaire);
		return commentaire;
	}
	@GetMapping("/afficher")
	public List<Commentaire> findAll(){
		return commentaireService.findALL();
	}
	@GetMapping("/{id}")
	public Commentaire findById(@PathVariable Integer id){
		return 		commentaireService.findById(id);
	}

	@DeleteMapping("/supprimer/{id}")
	public void supprimer(@PathVariable Integer id){
		commentaireService.delete(id);
	}
	@PostMapping("/maj/{id}")
	public Commentaire update(@RequestBody Commentaire commentaire,@PathVariable Integer id ) {
		commentaireService.update(id,commentaire);
		return commentaire;		
	}
}
