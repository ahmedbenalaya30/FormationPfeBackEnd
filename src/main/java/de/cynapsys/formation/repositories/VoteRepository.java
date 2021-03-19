package de.cynapsys.formation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.cynapsys.formation.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer>{
    public List<Vote> findAllByOrderByIdAsc();
}
