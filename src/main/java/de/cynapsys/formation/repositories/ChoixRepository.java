package de.cynapsys.formation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.cynapsys.formation.model.Choix;

public interface ChoixRepository  extends JpaRepository<Choix, Integer> {
    public List<Choix> findAllByOrderByIdAsc();

}
