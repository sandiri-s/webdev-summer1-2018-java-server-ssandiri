package courseapp.repositories;

import org.springframework.data.repository.CrudRepository;

import courseapp.models.FillInTheBlanksExamQuestion;

public interface FillInTheBlanksExamQuestionRepository
	extends CrudRepository<FillInTheBlanksExamQuestion, Integer>{
}