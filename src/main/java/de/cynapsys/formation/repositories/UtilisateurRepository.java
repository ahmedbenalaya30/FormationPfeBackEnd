package de.cynapsys.formation.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import de.cynapsys.formation.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	Optional<Utilisateur> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
