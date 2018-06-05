package courseapp.repositories;


import org.springframework.data.repository.CrudRepository;

import courseapp.models.BaseExamQuestion;

public interface BaseExamQuestionRepository
	extends CrudRepository<BaseExamQuestion, Integer>{
}