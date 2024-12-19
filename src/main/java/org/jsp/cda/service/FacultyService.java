package org.jsp.cda.service;

import java.time.LocalTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FacultyService {

	ResponseEntity<?> saveFaculty(int uid, MultipartFile file);

	ResponseEntity<?> findAllFaculty();
	
	ResponseEntity<?> updatePhoto(int id, MultipartFile file);	

	ResponseEntity<?> updateInfo(int id, String email, String phone, LocalTime officeHours);

	ResponseEntity<?> findFacultyById(int fid);

	ResponseEntity<?> assignDepartmentToFacultyProfile(int uid, int did);

}
