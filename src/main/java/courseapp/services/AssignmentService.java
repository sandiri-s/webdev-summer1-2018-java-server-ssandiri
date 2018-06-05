package courseapp.services;

import java.util.Date;
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

import courseapp.models.Lesson;
import courseapp.models.Assignment;
import courseapp.repositories.LessonRepository;
import courseapp.repositories.AssignmentRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {
	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	AssignmentRepository assignmentRepository;
	
	@PostMapping("/api/lesson/{lessonId}/assignment")
	public Assignment createAssignment(
			@PathVariable("lessonId") int lessonId,
			@RequestBody Assignment newAssignment) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newAssignment.setLesson(lesson);
			return assignmentRepository.save(newAssignment);

		}
		return null;		
	}
	
	@GetMapping("/api/lesson/{lessonId}/assignment")
	public List<Assignment> findAllAssignmentsForLesson(
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getWidgets()
					.stream()
					.filter(widget -> widget.getWidgetType().equals("Assignment"))
					.map(widget -> assignmentRepository.findById(widget.getId()).get())
					.collect(Collectors.toList());
		}
		return null;		
	}
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignment(@PathVariable("assignmentId") int assignmentId)
	{	

		assignmentRepository.deleteById(assignmentId);
	}
	
	@GetMapping("/api/assignment/{assignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int assignmentId)
	{
		return  assignmentRepository.findById(assignmentId).get();
	}
	
	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments()
	{
		return (List<Assignment>) assignmentRepository.findAll();
	}
}