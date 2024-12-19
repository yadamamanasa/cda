package org.jsp.cda.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.dao.StudentDao;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.Department;
import org.jsp.cda.entity.Student;
import org.jsp.cda.entity.User;
import org.jsp.cda.exceptionclasses.NoDepartmentFoundException;
import org.jsp.cda.exceptionclasses.NoStudentFoundException;
import org.jsp.cda.exceptionclasses.UserNotFoundException;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.jsp.cda.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentServiceImpl implements StudentService {

	private static final String FOLDER_PATH = "C:\\Users\\yadam\\Desktop\\AdvanceReact\\vite-project\\public\\Imges";

	@Autowired
	private StudentDao studentDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<?> saveStudent(int id, Student student) {

		Optional<User> optional = userDao.findUserById(id);

		if(optional.isEmpty())
			throw NoStudentFoundException.builder().message("No Student Found in Database").build(); 

		User user = optional.get();

		student.setUser(user);

		student = studentDao.saveStudent(student);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Student Saved  Successfully").body(student).build());
	}

	@Override
	public ResponseEntity<?> saveStudent(int uid, MultipartFile file) {
		Optional<User> optional = userDao.findUserById(uid);

		if (optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid User Id : " + uid).build();
		User user = optional.get();
		String photo = FOLDER_PATH + UUID.randomUUID() + file.getOriginalFilename();
		try {
			file.transferTo(new File(photo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Student student = Student.builder().id(uid).photo(photo).user(user).build();

		studentDao.saveStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Student saved successfully").body(student).build());
	}

	@Override
	public ResponseEntity<?> findAllStudent() {
		List<Student> student = studentDao.findAllStudent();
		if (student.isEmpty())
			throw NoStudentFoundException.builder().message("No Student found").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Student Found Successfully").body(student).build());

	}

	@Override
	public ResponseEntity<?> findStudentById(int id) {
		Optional<Student> optional = studentDao.findStudentById(id);
		if (optional.isEmpty())
			throw NoStudentFoundException.builder().message("No Student found by this id").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Student Found Successfully by id").body(optional.get()).build());

	}

	@Override
	public ResponseEntity<?> setYearToStudent(int sid, String year) {
		Optional<Student> optional = studentDao.findStudentById(sid);
		if (optional.isEmpty())
			throw NoStudentFoundException.builder().message("Invalid Student Id : " + sid).build();
		Student student = optional.get();
		student.setYear(year);
		student = studentDao.saveStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Year Updated Successfully").body(student).build());

	}

	@Override
	public ResponseEntity<?> assignDepartmentToStudent(int sid, int did) {
		Optional<Department> optional1 = departmentDao.findDepartmentById(did);
		if (optional1.isEmpty())
			throw NoDepartmentFoundException.builder().message("Invalid Department id :" + did).build();
		Optional<Student> optional2 = studentDao.findStudentById(sid);
		if (optional2.isEmpty())
			throw NoStudentFoundException.builder().message("Invalid Student Id :" + sid).build();
		Department department = optional1.get();
		Student student = optional2.get();
		student.setDepartment(department);
		student = studentDao.saveStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Department Assigned to Student Successfully").body(student).build());

	}

	
}
