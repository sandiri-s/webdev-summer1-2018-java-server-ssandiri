package courseapp.repositories;


import org.springframework.data.repository.CrudRepository;

import courseapp.models.TrueOrFalseExamQuestion;

public interface TrueOrFalseExamQuestionRepository
	extends CrudRepository<TrueOrFalseExamQuestion, Integer>{}