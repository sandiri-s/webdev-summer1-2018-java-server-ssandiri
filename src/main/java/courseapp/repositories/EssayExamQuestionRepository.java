package courseapp.repositories;


import org.springframework.data.repository.CrudRepository;

import courseapp.models.EssayExamQuestion;

public interface EssayExamQuestionRepository
	extends CrudRepository<EssayExamQuestion, Integer>{
}