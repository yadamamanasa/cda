package org.jsp.cda.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.CourseDao;
import org.jsp.cda.dao.FacultyDao;
import org.jsp.cda.entity.Course;
import org.jsp.cda.entity.Faculty;
import org.jsp.cda.exceptionclasses.NoCourseFoundException;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.jsp.cda.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private FacultyDao facultyDao;

	@Override
	public ResponseEntity<?> saveCourse(Course course) {
		
		course = courseDao.saveCourse(course);
		return   ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Student saved successfully").body(course).build());
	}

	@Override
	public ResponseEntity<?> findAllCourse() {
		List<Course> course = courseDao.findAllCourse();
		if(course.isEmpty())
			throw NoCourseFoundException.builder().message("No Course Found in Database Table").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("All Courses Found Successfully").body(course).build());
		
	}

	@Override
	public ResponseEntity<?> findCourseById(int id) {
		Optional<Course> optional = courseDao.findCourseById(id);
		if(optional.isEmpty())
			throw NoCourseFoundException.builder().message("Invalid Course Id :" + id).build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Course Found Successfully").body(optional.get()).build());
		
		
	}

	@Override
	public ResponseEntity<?> deleteCourseById(int id) {
		Optional<Course> optional = courseDao.findCourseById(id);
		if(optional.isEmpty())
			throw NoCourseFoundException.builder().message("Invalid Course Id : ").build();
		courseDao.deleteCourseById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Course deleted").body("Course Deleted Successfully").build());
		
	}

	@Override
	public ResponseEntity<?> assignFacultyToCourse(int cid, int fid) {
		Optional<Faculty> optional1 = facultyDao.findFacultyById(fid);
		if(optional1.isEmpty())
			throw new RuntimeException("Ivalid Faculty Id : "+fid);
		Optional<Course> optional2 = courseDao.findCourseById(cid);
		if(optional2.isEmpty())
			throw new RuntimeException("Ivalid Course Id : "+cid);
		Faculty faculty = optional1.get();
		Course course = optional2.get();
		course.setFaculty(faculty);
		course =  courseDao.saveCourse(course);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Faculty Assigned To Course Successfully...").body(course).build());
	}
	

}
