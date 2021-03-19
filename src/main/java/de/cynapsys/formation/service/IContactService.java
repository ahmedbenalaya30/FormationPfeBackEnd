package de.cynapsys.formation.service;

import java.util.List;

import de.cynapsys.formation.model.Contact;


	public interface IContactService {
		public void add(Contact contact);
		public void delete(Integer id);
		public void update(Integer id, Contact contact);
		public List<Contact> findALL();
		public Contact findById(Integer id );

}
