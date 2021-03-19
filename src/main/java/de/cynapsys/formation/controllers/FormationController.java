package de.cynapsys.formation.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import de.cynapsys.formation.model.Formation;
import de.cynapsys.formation.model.Utilisateur;
import de.cynapsys.formation.model.f;
import de.cynapsys.formation.repositories.UtilisateurRepository;
import de.cynapsys.formation.service.IFormationService;
import de.cynapsys.formation.service.IUtilisateurService;

@RestController
@RequestMapping("/formation")
@CrossOrigin
public class FormationController {
	@Autowired
	IFormationService formationService ; 
	
	@Autowired
    public JavaMailSender emailSender;
	
	@Autowired
	UtilisateurRepository userRepository;
	

	@Autowired
	IUtilisateurService utilisateurService;

	@PostMapping("/ajouter")
	public Formation ajouterUtilisateur(@RequestBody Formation formation) {
		formationService.add(formation);
		return formation;
	}

	@GetMapping("/afficher")
	public List<Formation> findAll() {
		return formationService.findALL();
	}

	@GetMapping("/{id}")
	public Formation findById(@PathVariable Integer id)
	{
		return 		formationService.findById(id);
	}

	@DeleteMapping("/supprimer/{id}")
	public void supprimer(@PathVariable Integer id) {
		formationService.delete(id);
	}
	
	@PostMapping("/maj/{id}")
	public Formation update(@RequestBody Formation formation ,@PathVariable Integer id ) {
		formationService.update(id,formation);
		return formation;		
	}

	@PostMapping("/ajouterUsers/{id}")
	public Formation  ajouterChoixById(@RequestBody List<Integer> invites ,@PathVariable Integer id)
	{
		Formation formation=formationService.findById(id);
		List<Utilisateur> ch = formation.getInvites();
		//		for (int i = 0; i < choix.size(); i++) {
		//			Choix choix1=choixService.findById(choix.get(i));
		//			ch.add(choix1);
		//		}
		ch.addAll(utilisateurService.findById(invites));
		formationService.AjouterUser(formation, ch);
		return formation;
	}
	
	@PostMapping("/ajouterFeedback/{id}")
	public Formation ajouterFeedback(@RequestBody String ch , @PathVariable Integer id)
	{
		return formationService.ajouterFeedback(ch, id);
	}
	//	@GetMapping("/afficherFeedback/{id}")
	//	public Map<String,Double> afficher(@PathVariable Integer id )
	//	{
	//		Formation formation=formationService.findById(id);
	//		return formation.getNotes();
	//	}
	//	@GetMapping("/afficherNotes/{id}")
	//	public List<Double> afficherNotes(@PathVariable Integer id )
	//	{
	//		Formation formation=formationService.findById(id);
	//		Set<Map.Entry<String,Double>> s =  formation.getNotes().entrySet();
	//		List<Double> a=new ArrayList<Double>();
	//		for (Entry<String, Double> it: s) 
	//		{ 
	//			a.add(it.getValue()) ;
	//		}
	//		return a;
	//	}
	//	@GetMapping("/afficherCriteres/{id}")
	//	public List<String> afficherCriteres(@PathVariable Integer id)
	//	{
	//		Formation formation=formationService.findById(id);
	//		Set<Map.Entry<String,Double>> s =  formation.getNotes().entrySet();
	//		List<String> a=new ArrayList<String>();
	//		for (Entry<String, Double> it: s) 
	//		{ 
	//
	//			a.add(it.getKey()) ;
	//		}
	//		return a;
	//	}
	@PostMapping("/verifierInvites/{id}")
	public boolean verifierInvites(@RequestBody Formation formation ,@PathVariable Integer id ){
		boolean verif=false;
		List<Utilisateur> Users=formation.getInvites();
		for (int i = 0; i < Users.size(); i++) {
			Utilisateur user=Users.get(i);
			if (user.getId().equals(id))
				verif=true;
		}
		return verif;
	}
	
	@PostMapping("/verifierFeedbackeurs/{id}")
	public boolean verifierFeedbackeurs(@RequestBody Formation formation ,@PathVariable Integer id ){
		boolean verif=false;
		List<Utilisateur> Users=formation.getFeedbackeurs();
		for (int i = 0; i < Users.size(); i++) {
			Utilisateur user=Users.get(i);
			if (user.getId().equals(id))
				verif=true;
		}
		return verif;
	}
	
	@PostMapping("/ajouterInvite/{idU}")
	public Formation ajouterInvites(@RequestBody Formation formation, @PathVariable Integer idU){
		Utilisateur user=utilisateurService.findById(idU);
		List<Utilisateur> ch = formation.getInvites();
		ch.add(user);
		formationService.ajouterInvite(formation,ch);
		return  formation;
	}
	
	@PostMapping("/ajouterFeedbackeur/{idU}")
	public Formation ajouterFeedbackeur(@RequestBody Formation formation, @PathVariable Integer idU){
		formationService.ajouterFeedbackeur(formation, idU);
		return  formation;
	}
	
	
	
	@GetMapping("/afficherFormationfini/{username}")
	public List<Formation> afficherFormation(@PathVariable String username)
	{
		LocalDate d=LocalDate.now();
		List<Formation> f=formationService.findALL();
		List<Formation> c=new ArrayList<Formation>();
		for (int i=0;i<f.size();i++)
		{
		   if(f.get(i).getFeedback().size()!=0)
			if (formationService.verifFeedbackeur(f.get(i), username)==false)
			    if (formationService.ComparerDate(d, f.get(i).getDateFin()))
				c.add(f.get(i));
		}
		return c;
	}
	@GetMapping("/afficherFormationNonfini")
	public List<Formation> afficherFormation1()
	{
		LocalDate d=LocalDate.now();
		List<Formation> f=formationService.findALL();
		List<Formation> c=new ArrayList<Formation>();
		for (int i=0;i<f.size();i++)
		{
			if (!(formationService.ComparerDate(d, f.get(i).getDateFin())))
				c.add(f.get(i));
		}
		return c;
	}
	
	@PostMapping("/nombredeFeedback")
	public int NombredeFeedback(@RequestBody Formation f )
	{
		return f.getFeedback().size();
	}
	
	@PostMapping("/DonnerNotes/{IdF}")
	public void DonnerNotes(@RequestBody List<Integer> notes,@PathVariable Integer IdF )
	{
		if (notes.size()!=0)
		{
		Formation f=formationService.findById(IdF);
		formationService.DonnerNotes(f, notes);
	}
	}
	@GetMapping("/verifierFeedback")
	public boolean verifierFeedback(@RequestBody Formation f)
	{
		if (f.getFeedback().size()==0)
			return false ; 
			else 
				return true ;
	}
	
	@PostMapping("/Sendmail/{id}")
	public void sendSimpleEmail(@RequestBody Formation f ,@PathVariable Integer id) {
		 
		Utilisateur user=utilisateurService.findById(id);
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
         
        message.setTo(user.getMail());
        message.setSubject("Formation");
        message.setText(f.getDescription());
        // Send Message!
        this.emailSender.send(message);
 
	}
	
	@PostMapping("ConsulterFormation")
	public List<Formation> ConsulterFormation(@RequestBody String ch)
	{
		Optional<Utilisateur> user= userRepository.findByUsername(ch);
		return formationService.consulter(user.get().getId());
	}

	@PostMapping("/Annuler/{idF}")
	public void sendSimpleEmailListe(@PathVariable Integer idF) {	 
Formation f=formationService.findById(idF)  ;  
List<Utilisateur> users=f.getInvites();
// Create a Simple MailMessage.
for (int i=0;i<users.size();i++)
{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(users.get(i).getMail());
        message.setSubject("Annulation");
        message.setText(f.getDescription());
        // Send Message!
        this.emailSender.send(message);
}
	}
	
	@PostMapping("/sendAllmail/{idF}")
	public void sendallSimpleEmailListe(@RequestBody List<Integer> liste, @PathVariable Integer idF) {	 
Formation f=formationService.findById(idF)  ;  
List<Utilisateur> users=utilisateurService.findById(liste);
// Create a Simple MailMessage.
for (int i=0;i<users.size();i++)
{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(users.get(i).getMail());
        message.setSubject("Nouvelle formation");
        message.setText(f.getDescription());
        // Send Message!
        this.emailSender.send(message);
}
	}
	
	@PostMapping("/ajout")
	public Formation ajouter(@RequestBody f formation) {
Formation f=new Formation();
f.setDescription(formation.getDescription()); 
f.setDateDebut(formation.getDateDebut());
f.setDateFin(formation.getDateFin());
List<Utilisateur> u=utilisateurService.findById(formation.getInvites());
f.setInvites(u);
formationService.add(f);
for (int i=0;i<u.size();i++)
{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(u.get(i).getMail());
        message.setSubject("Nouvelle formation");
        message.setText(f.getDescription());
        // Send Message!
        this.emailSender.send(message);
}
		return f;
	}
	
	@PostMapping("/update/{idF}")
	public void update(@RequestBody f formation,@PathVariable Integer idF)
	{
		Formation f1=formationService.findById(idF);
f1.setDescription(formation.getDescription());
f1.setDateDebut(formation.getDateDebut());
f1.setDateFin(formation.getDateFin());
List<Utilisateur> u=utilisateurService.findById(formation.getInvites());
u.addAll(f1.getInvites());
f1.setInvites(u);
formationService.add(f1);
for (int i=0;i<u.size();i++)
{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(u.get(i).getMail());
        message.setSubject("Nouvelle formation");
        message.setText(formation.getDescription());
        // Send Message!
        this.emailSender.send(message);
}
	}
}
	