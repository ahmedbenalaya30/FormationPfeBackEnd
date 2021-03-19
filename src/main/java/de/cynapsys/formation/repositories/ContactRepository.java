package de.cynapsys.formation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.cynapsys.formation.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
