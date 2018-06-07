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
import courseapp.models.Assignment;
import courseapp.models.EssayExamQuestion;
import courseapp.repositories.EssayExamQuestionRepository;
import courseapp.repositories.ExamRepository;


@RestController
@CrossOrigin(origins = "*")
public class EssayExamQuestionService {

	@Autowired
	ExamRepository examRepo;

	@Autowired
	EssayExamQuestionRepository essayRepo;

	@PostMapping("/api/exam/{examId}/essay")
	public EssayExamQuestion createEssayExamQuestion(
			@PathVariable("examId") int examId,
			@RequestBody EssayExamQuestion newEssayQuestion) {
		Optional<Exam> data = examRepo.findById(examId);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newEssayQuestion.setExam(exam);
			return essayRepo.save(newEssayQuestion);
		}
		return null;		
	}
	
	@PutMapping("/api/essay/{essayId}")
	public EssayExamQuestion updateEssayQuestion(@PathVariable("essayId") int essayId, @RequestBody EssayExamQuestion newEssayExamQuestion) {
		Optional<EssayExamQuestion> data = essayRepo.findById(essayId);
		if(data.isPresent()) {
			EssayExamQuestion question = data.get();
			question.setTitle(newEssayExamQuestion.getTitle());
			question.setDescription(newEssayExamQuestion.getDescription());
			question.setPoints(newEssayExamQuestion.getPoints());
			essayRepo.save(question);
			return question;
		}
		return null;
	}

	@GetMapping("/api/exam/{examId}/essay")
	public List<EssayExamQuestion> findAllEssayQuestionsForExam(
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepo.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			return exam.getQuestions()
					.stream()
					.filter(question -> question.getType().equals("Essay"))
					.map(question -> essayRepo.findById(question.getId()).get())
					.collect(Collectors.toList());
		}
		return null;		
	}
	

	
}