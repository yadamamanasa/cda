package org.jsp.cda.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.dao.FacultyDao;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.Department;
import org.jsp.cda.entity.Faculty;
import org.jsp.cda.entity.User;
import org.jsp.cda.exceptionclasses.InvalidFacultyIdException;
import org.jsp.cda.exceptionclasses.NoFacultyFoundException;
import org.jsp.cda.exceptionclasses.UserNotFoundException;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.jsp.cda.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FacultyServiceImpl implements FacultyService {
	
	private static final String FOLDER_PATH = "C:\\Users\\yadam\\Desktop\\AdvanceReact\\vite-project\\public\\Imges\\faculty\\	";
	
	@Autowired
	private FacultyDao facultydao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveFaculty(int uid , MultipartFile file) {
		
		Optional<User> optional = userDao.findUserById(uid);
		if(optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid User Id ..." + uid).build();
		User user = optional.get();
		String photo = FOLDER_PATH  +  UUID.randomUUID() + file.getOriginalFilename();
		try {
			file.transferTo(new File(photo));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		Faculty faculty = Faculty.builder().photo(photo).id(uid).user(user).build();
		faculty = facultydao.saveFaculty(faculty);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Faculty Profile Saved Successfully...").body(faculty	).build());
	}

	@Override
	public ResponseEntity<?> findAllFaculty() {
		List<Faculty> faculty = facultydao.findAllFaculty();
		if(faculty.isEmpty())
			throw NoFacultyFoundException.builder().message("No Faculty Profiles Present in Databse Table...").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("All Faculty Found Successfully").body(faculty).build());
		
	}
	@Override
	public ResponseEntity<?> findFacultyById(int fid) {
		Optional<Faculty> optional = facultydao.findFacultyById(fid);
		if(optional.isEmpty())
			throw InvalidFacultyIdException.builder().message("Invalid Faculty id").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Faculty Found Successfully").body(optional).build());
		
	
	}
	
	
	@Override
	public ResponseEntity<?> updatePhoto(int id, MultipartFile file) {
		Optional<Faculty> optional = facultydao.findFacultyById(id);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid faculty Profile Id" + id);
		Faculty faculty = optional.get();
		String photo = FOLDER_PATH +  UUID.randomUUID() + file.getOriginalFilename();
		try {
			file.transferTo(new File(photo));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		faculty.setPhoto(photo);
		faculty = facultydao.saveFaculty(faculty);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("Photo Uploaded Successfully...").body(faculty).build());
	
	}
	
	@Override
	public ResponseEntity<?> updateInfo(int id,String email, String phone, LocalTime officeHours) {
		Optional<Faculty> optional1 = facultydao.findFacultyById(id);
		if(optional1.isEmpty())
			throw InvalidFacultyIdException.builder().message("Invalid Faculty id:"+id).build();
		
		Optional<User> optional2 = userDao.findUserById(id);
		if(optional2.isEmpty())
			throw InvalidFacultyIdException.builder().message("Invalid User id:"+id).build();
		User user = optional2.get();
		Faculty faculty = optional1.get();
		faculty.setOfficeHours(officeHours);
		faculty = facultydao.saveFaculty(faculty);
		user.setEmail(email);
		user.setPhone(phone);
		user = userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Faculty updated Successfully").body(faculty).build());
		
	
	}

	@Override
	public ResponseEntity<?> assignDepartmentToFacultyProfile(int uid, int did) {
		Optional<Department> optional1 = departmentDao.findDepartmentById(did);
		if(optional1.isEmpty())
			throw NoFacultyFoundException.builder().message("Invalid Department id:"+did).build();
		Optional<Faculty> optional2 = facultydao.findFacultyById(uid);
		if(optional1.isEmpty())
			throw InvalidFacultyIdException.builder().message("Invalid Faculty id:"+did).build();
		Faculty faculty =optional2.get();
		Department department = optional1.get();
		faculty.setDepartment(department);
		faculty = facultydao.saveFaculty(faculty);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Department assigned successfully to faculty profile").body(faculty).build());
		
	}

	

	

}
