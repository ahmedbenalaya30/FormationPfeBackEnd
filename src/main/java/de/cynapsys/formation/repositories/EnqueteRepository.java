package de.cynapsys.formation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.cynapsys.formation.model.Enquete;

public interface EnqueteRepository extends JpaRepository<Enquete, Integer>{
    public List<Enquete> findAllByOrderByIdAsc();

}
