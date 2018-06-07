
package courseapp.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import courseapp.models.Exam;
import courseapp.models.MultipleChoiceExamQuestion;
import courseapp.models.TrueOrFalseExamQuestion;
import courseapp.repositories.TrueOrFalseExamQuestionRepository;
import courseapp.repositories.ExamRepository;


@RestController
@CrossOrigin(origins = "*")
public class TrueOrFalseExamQuestionService {

	@Autowired
	ExamRepository examRepo;

	@Autowired
	TrueOrFalseExamQuestionRepository trueRepo;

	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalseExamQuestion createTFQuestion(
			@PathVariable("examId") int examId,
			@RequestBody TrueOrFalseExamQuestion newTFQuestion) {
		Optional<Exam> data = examRepo.findById(examId);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newTFQuestion.setExam(exam);
			return trueRepo.save(newTFQuestion);
		}
		return null;		
	}
	
	

	@GetMapping("/api/exam/{examId}/truefalse")
	public List<TrueOrFalseExamQuestion> findAllTFQuestionsForExam(
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepo.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			return exam.getQuestions()
					.stream()
					.filter(question -> question.getType().equals("TrueFalse"))
					.map(question -> trueRepo.findById(question.getId()).get())
					.collect(Collectors.toList());
		}
		return null;		
	}
	
	@PutMapping("/api/truefalse/{truefalseId}")
	public TrueOrFalseExamQuestion updateTFQuestion(@PathVariable("truefalseId") int truefalseId, @RequestBody TrueOrFalseExamQuestion newTrueFalseQuestion) {
		Optional<TrueOrFalseExamQuestion> data = trueRepo.findById(truefalseId);
		if(data.isPresent()) {
			TrueOrFalseExamQuestion question = data.get();
			question.setTitle(newTrueFalseQuestion.getTitle());
			question.setDescription(newTrueFalseQuestion.getDescription());
			question.setPoints(newTrueFalseQuestion.getPoints());
			question.setIsTrue(newTrueFalseQuestion.getIsTrue());
			trueRepo.save(question);
			return question;
		}
		return null;
	}
	

	
}