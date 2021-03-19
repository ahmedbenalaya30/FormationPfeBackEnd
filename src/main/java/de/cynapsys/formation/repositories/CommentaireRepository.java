package de.cynapsys.formation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import de.cynapsys.formation.model.Commentaire;

public interface CommentaireRepository  extends   JpaRepository<Commentaire , Integer>{
    public List<Commentaire> findAllByOrderByDateAsc();
}
