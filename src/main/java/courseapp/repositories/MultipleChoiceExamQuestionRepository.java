package courseapp.repositories;

import org.springframework.data.repository.CrudRepository;

import courseapp.models.MultipleChoiceExamQuestion;

public interface MultipleChoiceExamQuestionRepository
	extends CrudRepository<MultipleChoiceExamQuestion, Integer>{
}