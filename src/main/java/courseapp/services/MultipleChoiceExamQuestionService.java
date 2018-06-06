
package courseapp.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import courseapp.models.Exam;
import courseapp.models.MultipleChoiceExamQuestion;
import courseapp.repositories.MultipleChoiceExamQuestionRepository;
import courseapp.repositories.ExamRepository;


@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceExamQuestionService {

	@Autowired
	ExamRepository examRepo;

	@Autowired
	MultipleChoiceExamQuestionRepository multiRepo;

	@PostMapping("/api/exam/{examId}/choice")
	public MultipleChoiceExamQuestion createMultiQuestion(
			@PathVariable("examId") int examId,
			@RequestBody MultipleChoiceExamQuestion newMultiQuestion) {
		Optional<Exam> data = examRepo.findById(examId);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newMultiQuestion.setExam(exam);
			return multiRepo.save(newMultiQuestion);
		}
		return null;		
	}
	
	

	@GetMapping("/api/exam/{examId}/choice")
	public List<MultipleChoiceExamQuestion> findAllChoiceQuestionsForExam(
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepo.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			return exam.getQuestions()
					.stream()
					.filter(question -> question.getType().equals("Choice"))
					.map(question -> multiRepo.findById(question.getId()).get())
					.collect(Collectors.toList());
		}
		return null;		
	}
	

	
}