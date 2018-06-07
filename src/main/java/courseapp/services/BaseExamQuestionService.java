package courseapp.services;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import courseapp.models.Exam;

import courseapp.models.BaseExamQuestion;

import courseapp.repositories.BaseExamQuestionRepository;

import courseapp.repositories.ExamRepository;


@RestController
@CrossOrigin(origins = "*")
public class BaseExamQuestionService {

	@Autowired
	ExamRepository examRepo;

	@Autowired
	BaseExamQuestionRepository baseRepo;

	
	

	@GetMapping("/api/exam/{examId}/question")
	public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepo.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			return questions;
		}
		return null;
	}
	
	@DeleteMapping("/api/question/{questionId}")
	public void deleteExam(@PathVariable("questionId") int id) {
		baseRepo.deleteById(id);
	}
	
}