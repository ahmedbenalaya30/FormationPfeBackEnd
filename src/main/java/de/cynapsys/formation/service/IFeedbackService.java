package de.cynapsys.formation.service;

import de.cynapsys.formation.model.Feedback;

public interface IFeedbackService {

	void add(Feedback formation);

	void delete(Integer id);

	void update(Integer id, Feedback feedback);

}
