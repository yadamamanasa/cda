package org.jsp.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.CourseDao;
import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.entity.Course;
import org.jsp.cda.entity.Department;
import org.jsp.cda.repository.CourseRepository;
import org.jsp.cda.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CourseDaoImpl implements CourseDao{
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course saveCourse(Course course) {

    return courseRepository.save(course);
		
	}

	@Override
	public List<Course> findAllCourse() {
		return courseRepository.findAll();
	}

	@Override
	public Optional<Course> findCourseById(int id) {
		return courseRepository.findById(id);
	}

	@Override
	public void deleteCourseById(int id) {
		courseRepository.deleteById(id);
	}

}
