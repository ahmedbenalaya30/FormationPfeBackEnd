package de.cynapsys.formation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import de.cynapsys.formation.model.Formation;

public interface FormationRepository extends   JpaRepository<Formation, Integer> {
    public List<Formation> findAllByOrderByIdAsc();

}
