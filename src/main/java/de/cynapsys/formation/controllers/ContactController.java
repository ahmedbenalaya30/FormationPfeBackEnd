package de.cynapsys.formation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cynapsys.formation.model.Contact;
import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.service.IContactService;
import de.cynapsys.formation.service.IUtilisateurService;

@RestController
@RequestMapping("/contact")
@CrossOrigin
public class ContactController {
	@Autowired
	IContactService  contactService;

	@Autowired
	IUtilisateurService utilisateurService; 
	
	@Autowired
    public JavaMailSender emailSender;
	
	 @Value("${spring.mail.username}")
	    private String mailAdmin;

	@PostMapping("/ajouter/{id}")
	public Contact ajouterContact (@RequestBody Contact contact , @PathVariable Integer id)
	{
		Utilisateur user=utilisateurService.findById(id);
		contact.setUser(user);
		contactService.add(contact);
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(user.getMail());
//        message.setTo(mailAdmin);
//        message.setSubject("Nouveau contact");
//        message.setText(contact.getCommentaire());
//        // Send Message!
//        this.emailSender.send(message);
		return contact;
	}
	
	@GetMapping("/afficher")
	public List<Contact> findAll() {
		return contactService.findALL();
	}
	@GetMapping("/{id}")
	public Contact findById(@PathVariable Integer id)
	{
		return 		contactService.findById(id);
	}

	@DeleteMapping("/supprimer/{id}")
	public void supprimer(@PathVariable Integer id) {
		contactService.delete(id);
	}
	@PostMapping("/maj/{id}")
	public Contact update(@RequestBody Contact contact,@PathVariable Integer id ) {
		contactService.update(id,contact);
		return contact;		
	}



}
