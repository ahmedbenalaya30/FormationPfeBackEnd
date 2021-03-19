package de.cynapsys.formation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.cynapsys.formation.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
