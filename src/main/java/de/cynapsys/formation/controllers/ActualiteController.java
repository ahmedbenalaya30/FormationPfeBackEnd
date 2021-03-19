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

import de.cynapsys.formation.model.Actualite;
import de.cynapsys.formation.model.actu;
import de.cynapsys.formation.service.IActualiteService;

@RestController
@RequestMapping("/actualite")
@CrossOrigin
public class ActualiteController {
	@Autowired
	IActualiteService actualiteService;
	
	@PostMapping("/ajouter")
	public Actualite ajouter (@RequestBody Actualite actualite) {
		actualiteService.add(actualite);
		return actualite;
	}
	@GetMapping("/afficher")
	public List<Actualite> findAll() {
		return actualiteService.findALL();
	}
	@GetMapping("/{id}")
	public Actualite findById(@PathVariable Integer id)
	{
		return 		actualiteService.findById(id);
	}

	@DeleteMapping("/supprimer/{id}")
	public void supprimer(@PathVariable Integer id) {
		actualiteService.delete(id);
	}
	@PostMapping("/maj/{id}")
	public Actualite update(@RequestBody Actualite actualite,@PathVariable Integer id ) {
		actualiteService.update(id,actualite);
		return actualite;		
	}
	@PostMapping("/visible")
	public void visibile(@RequestBody Actualite actualite){
		actualiteService.visible(actualite);
	}
	
	@PostMapping("/invisible")
	public void invisibile(@RequestBody Actualite actualite){
		actualiteService.invisible(actualite);
	}
	
	@GetMapping("/a")
	public actu get()
	{
		String ch="" ;
		List<Actualite> a=actualiteService.findALL();
		
		for (int i=0;i<a.size();i++)
		{
			ch=ch+"*****"+a.get(i).getMessage();
		}
		actu ahmed=new actu(ch);
		return ahmed ;
	}
	
}
