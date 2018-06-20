package courseapp.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import courseapp.models.Course;
import courseapp.models.User;
import courseapp.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll(); 
	}
	
	@GetMapping("/api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		Date presentDate = new Date();
		course.setCreated(presentDate);
		course.setModified(presentDate);
		return courseRepository.save(course);
	}

	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}
	
	@GetMapping("/api/course/public")
	public Iterable<Course> findPublicCourses() {
		List<Course> courses = (List<Course>) courseRepository.findAll();
		List<Course> publicCourses = new ArrayList<Course>();
		for (Course course: courses) {
			if (course.getCourseType() == "public") {
				publicCourses.add(course);
			}
		}
		return publicCourses;
	}
}