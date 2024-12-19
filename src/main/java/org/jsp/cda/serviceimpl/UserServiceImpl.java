package org.jsp.cda.serviceimpl;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.jsp.cda.dao.AdministratorDao;
import org.jsp.cda.dao.FacultyDao;
import org.jsp.cda.dao.StudentDao;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.dto.AuthUser;
import org.jsp.cda.entity.Administrator;
import org.jsp.cda.entity.Faculty;
import org.jsp.cda.entity.Student;
import org.jsp.cda.entity.User;
import org.jsp.cda.exceptionclasses.InvalidCredentialException;
import org.jsp.cda.exceptionclasses.InvalidUserEmailException;
import org.jsp.cda.exceptionclasses.NoUserFoundException;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.jsp.cda.service.UserService;
import org.jsp.cda.util.MyUtil;
import org.jsp.cda.util.Role;
import org.jsp.cda.util.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private AdministratorDao administratorDao;
	@Autowired
	private FacultyDao facultyDao;
	@Autowired
	private StudentDao studentDao;

	@Override
	public ResponseEntity<?> saveUser(User user) {
		String photo = "C:\\Users\\yadam\\Desktop\\AdvanceReact\\vite-project\\public\\Imges\\M.jpg	";
		user.setStatus(UserStatus.IN_ACTIVE);
		user.setOtp(MyUtil.getOTP());
		user = userDao.saveUser(user);
		
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.addTo(user.getEmail());
			mimeMessageHelper.setSubject("Account Created");
			mimeMessageHelper.setText("<html><body style='color:crimson;'><h1>Hello!....."+user.getName() + " Your Account is Hacked... Within 24hrs  your balance is going to Zero balance <br> <br> <hr> <br>  Your OTP"+user.getOtp()+"</h1> </body></html>",true);
			javaMailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
		
//		if (user.getRole() == Role.ADMINISTRATOR)
//			administratorDao.saveAdministrator(
//					Administrator.builder().id(user.getId()).photo(photo).user(user).build());
//		else if (user.getRole() == Role.FACULTY)
//			facultyDao.saveFaculty(Faculty.builder().id(user.getId()).user(user).photo(photo)
//					.officeHours(LocalTime.of(8, 30)).build());
//		else
//			studentDao.saveStudent(Student.builder().id(user.getId()).photo(photo).user(user).build());
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("User Saved Successfully...").body(user).build());
	}


	@Override
	public ResponseEntity<?> findAllUser() {
		List<User> user = userDao.findAllUser();
		if(user.isEmpty())
			throw NoUserFoundException.builder().message("No User found").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("User Found Successfully").body(user).build());
		
	}

	@Override
	public ResponseEntity<?> findUserById(int id) {
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty()) 
			throw InvalidCredentialException.builder().message("Invalid User Id").build();
			
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("User Id Found ").body(optional.get()).build());
			
	}

	@Override
	public ResponseEntity<?> findByUsernameAndPassword(AuthUser authuser) {
		Optional<User> optional = userDao.findByUsernameAndPassword(authuser.getUsername(), authuser.getPassword());
		
		if(optional.isEmpty()) 
			throw InvalidCredentialException.builder().message("Invalid Username and Password").build();
			
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Login Successfully").body(optional.get()).build());
			
	}


	@Override
	public ResponseEntity<?> verifyOtp(int id, int otp) {
		Optional<User> optional = userDao.findUserById(id);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid User Id Unable to verify Otp..");
		User user = optional.get();
		if(otp!=user.getOtp())
			throw new RuntimeException("Invalid Otp Unable to verify the Otp");
		user.setStatus(UserStatus.ACTIVE);
		user = userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("OTP Verified Successfully").body(user).build());
		
	}


	@Override
	public ResponseEntity<?> findUserByEmail(String email) {
		Optional<User> optional = userDao.findUserByEmail(email);
		if(optional.isEmpty())
			throw InvalidUserEmailException.builder().message("No User Match By Email..").build();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("User Found Successfully").body(optional.get()).build());
		
	}

}
