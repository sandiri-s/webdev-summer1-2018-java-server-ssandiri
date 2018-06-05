package courseapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import courseapp.models.Exam;
import courseapp.models.Lesson;
import courseapp.models.MultipleChoiceExamQuestion;
import courseapp.models.BaseExamQuestion;
import courseapp.models.TrueOrFalseExamQuestion;
import courseapp.models.Widget;
import courseapp.repositories.ExamRepository;
import courseapp.repositories.LessonRepository;
import courseapp.repositories.MultipleChoiceExamQuestionRepository;
import courseapp.repositories.TrueOrFalseExamQuestionRepository;
import courseapp.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueOrFalseExamQuestionRepository trueFalseRepository;
	@Autowired
	MultipleChoiceExamQuestionRepository mutiRepo;

	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceExamQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceExamQuestion> optional = mutiRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@GetMapping("/api/truefalse/{questionId}")
	public TrueOrFalseExamQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueOrFalseExamQuestion> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{examId}/question")
	public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			int count = questions.size();
			return questions;
		}
		return null;
	}
}