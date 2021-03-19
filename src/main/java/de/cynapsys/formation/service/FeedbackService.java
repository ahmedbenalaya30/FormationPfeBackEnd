package de.cynapsys.formation.service;

import org.springframework.beans.factory.annotation.Autowired;

import de.cynapsys.formation.model.Feedback;
import de.cynapsys.formation.repositories.FeedbackRepository;

public class FeedbackService implements IFeedbackService {
	@Autowired FeedbackRepository feedbackRepository;

	@Override
	public void add(Feedback feedback){
		// TODO Auto-generated method stub
		feedbackRepository.save(feedback);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		feedbackRepository.deleteById(id);
	}
	@Override
	public void update(Integer id, Feedback feedback) {
		// TODO Auto-generated method stub
		feedbackRepository.save(feedback);
	}
	
}
