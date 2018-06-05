package courseapp.repositories;

import org.springframework.data.repository.CrudRepository;

import courseapp.models.Exam;

public interface ExamRepository
extends CrudRepository<Exam, Integer>{

}