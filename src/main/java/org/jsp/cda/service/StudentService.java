package org.jsp.cda.service;

import org.jsp.cda.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {

	ResponseEntity<?> saveStudent(int uid,MultipartFile file);

	ResponseEntity<?> findAllStudent();

	ResponseEntity<?> findStudentById(int id);

	ResponseEntity<?> setYearToStudent(int sid, String year);

	ResponseEntity<?> assignDepartmentToStudent(int sid, int did);

	ResponseEntity<?> saveStudent(int id, Student student);

}
