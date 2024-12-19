package org.jsp.cda.dao;


import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Faculty;



public interface FacultyDao {

	Faculty saveFaculty(Faculty faculty);

	List<Faculty> findAllFaculty();

	Optional<Faculty> findFacultyById(int fid);

	

}
