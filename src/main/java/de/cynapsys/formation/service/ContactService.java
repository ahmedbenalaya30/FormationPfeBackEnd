package de.cynapsys.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.cynapsys.formation.model.Contact;
import de.cynapsys.formation.repositories.ContactRepository;

@Service
@Transactional
public class ContactService implements IContactService {

	@Autowired
	ContactRepository contactRepository;
	
	@Override
	public void add(Contact contact) {
		// TODO Auto-generated method stub
		contactRepository.save(contact);
	}

	@Override
	public void delete(Integer id ) {
		// TODO Auto-generated method stub
		contactRepository.deleteById(id);


	}

	@Override
	public void update(Integer id,Contact contact) {
		// TODO Auto-generated method stub
		contactRepository.save(contact);


	}

	@Override
	public List<Contact> findALL() {
		// TODO Auto-generated method stub
		return 		contactRepository.findAll();	}

	@Override
	public Contact findById(Integer id) {
		// TODO Auto-generated method stub
		return 		contactRepository.getOne(id);
	}
	


}
