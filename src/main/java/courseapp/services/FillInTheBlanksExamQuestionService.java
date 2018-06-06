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
import courseapp.models.FillInTheBlanksExamQuestion;
import courseapp.repositories.FillInTheBlanksExamQuestionRepository;
import courseapp.repositories.ExamRepository;


@RestController
@CrossOrigin(origins = "*")
public class FillInTheBlanksExamQuestionService {

	@Autowired
	ExamRepository examRepo;

	@Autowired
	FillInTheBlanksExamQuestionRepository fIBRepo;

	@PostMapping("/api/exam/{examId}/blanks")
	public FillInTheBlanksExamQuestion createFIBQuestion(
			@PathVariable("examId") int examId,
			@RequestBody FillInTheBlanksExamQuestion newFIBQuestion) {
		Optional<Exam> data = examRepo.findById(examId);
		
		if(data.isPresent()) {
			Exam exam = data.get();
			newFIBQuestion.setExam(exam);
			return fIBRepo.save(newFIBQuestion);
		}
		return null;		
	}
	
	

	@GetMapping("/api/exam/{examId}/blanks")
	public List<FillInTheBlanksExamQuestion> findAllBlanksQuestionsForExam(
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepo.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			return exam.getQuestions()
					.stream()
					.filter(question -> question.getType().equals("Blanks"))
					.map(question -> fIBRepo.findById(question.getId()).get())
					.collect(Collectors.toList());
		}
		return null;		
	}
	

	
}