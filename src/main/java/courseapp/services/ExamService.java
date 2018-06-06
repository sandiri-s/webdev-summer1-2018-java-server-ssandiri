package courseapp.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import courseapp.models.Exam;
import courseapp.models.Lesson;

import courseapp.repositories.BaseExamQuestionRepository;
import courseapp.repositories.EssayExamQuestionRepository;
import courseapp.repositories.ExamRepository;
import courseapp.repositories.FillInTheBlanksExamQuestionRepository;
import courseapp.repositories.LessonRepository;
import courseapp.repositories.MultipleChoiceExamQuestionRepository;
import courseapp.repositories.TrueOrFalseExamQuestionRepository;


@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	LessonRepository lessonRepo;
	@Autowired
	ExamRepository examRepo;
	@Autowired
	TrueOrFalseExamQuestionRepository trueFalseRepo;
	@Autowired
	MultipleChoiceExamQuestionRepository multiRepo;
	@Autowired
	BaseExamQuestionRepository baseRepo;
	@Autowired
	FillInTheBlanksExamQuestionRepository fIBRepo;
	@Autowired
	EssayExamQuestionRepository essayRepo;
	
	
	
	@GetMapping("/api/exam")
	public Iterable<Exam> findAllExams() {
		return examRepo.findAll(); 
	}
	
	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepo.findById(examId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/exam")
	public List<Exam> findAllExamsForLesson(
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepo.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getWidgets()
					.stream()
					.filter(widget -> widget.getWidgetType().equals("Exam"))
					.map(widget -> examRepo.findById(widget.getId()).get())
					.collect(Collectors.toList());
		}
		return null;		
	}
	
	
	@PostMapping("/api/lesson/{lessonId}/exam")
	public Exam createExam(
			@PathVariable("lessonId") int lessonId,
			@RequestBody Exam newExam) {
		Optional<Lesson> data = lessonRepo.findById(lessonId);
		
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newExam.setLesson(lesson);
			return examRepo.save(newExam);

		}
		return null;		
	}

	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int id) {
		examRepo.deleteById(id);
	}


}