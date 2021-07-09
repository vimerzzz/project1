package edu.hust.appweb.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.hust.appweb.domain.ErrorCode;
import edu.hust.appweb.domain.InformationStudent;
import edu.hust.appweb.domain.InformationTeacher;
import edu.hust.appweb.domain.LastRegisterStudent;
import edu.hust.appweb.domain.LastRegisterTeacher;
import edu.hust.appweb.domain.User;
import edu.hust.appweb.repository.ErrorCodeRepository;
import edu.hust.appweb.repository.InformationStudentRepository;
import edu.hust.appweb.repository.InformationTeacherRepository;
import edu.hust.appweb.repository.LastRegisterStudentRepository;
import edu.hust.appweb.repository.LastRegisterTeacherRepository;
import edu.hust.appweb.repository.UserRepository;

@Controller
public class RegisterController {
	private String role;
	private int iduser;
	private int lastRegister;
	private LastRegisterStudent lastRegisterStudent;
	private LastRegisterTeacher lastRegisterTeacher;
	
	@Autowired
	ErrorCodeRepository errorCodeRepository;
	@Autowired
	InformationStudentRepository informationStudentRepository;
	@Autowired
	InformationTeacherRepository informationTeacherRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	LastRegisterStudentRepository lastRegisterStudentRepository;
	@Autowired
	LastRegisterTeacherRepository lastRegisterTeacherRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
	
	@RequestMapping("/admin/register-student")
	public String registerStudent(Model model) {
		lastRegisterStudent = lastRegisterStudentRepository.findStudentById(1);
		if(lastRegisterStudent == null) {
			lastRegister = 20200001;
			lastRegisterStudent = new LastRegisterStudent(lastRegister);
		}
		else {
			lastRegister = lastRegisterStudent.getIduser();
			lastRegister++;
			lastRegisterStudent.setIduser(lastRegister);
		}
		model.addAttribute("iduser", lastRegister);
		return "register-student";
	}
	
	@RequestMapping("/admin/register-teacher")
	public String registerTeacher(Model model) {
		lastRegisterTeacher = lastRegisterTeacherRepository.findTeacherById(1);
		if(lastRegisterTeacher == null) {
			lastRegister = 202001;
			lastRegisterTeacher = new LastRegisterTeacher(lastRegister);
		}
		else {
			lastRegister = lastRegisterTeacher.getIduser();
			lastRegister++;
			lastRegisterTeacher.setIduser(lastRegister);
		}
		model.addAttribute("iduser", lastRegister);
		return "register-teacher";
	}
	
	@PostMapping("/admin/register-student")
	public String registerForStudent(@RequestParam String piduser, @RequestParam String fullname, @RequestParam Date birth, @RequestParam String phone, @RequestParam String mail, @RequestParam String country, @RequestParam String classroom) {
		InformationStudent informationStudent = new InformationStudent(fullname, piduser, birth, phone, mail, country, classroom);
		informationStudentRepository.save(informationStudent);
		iduser = informationStudent.getIduser();
		role = "ROLE_STUDENT";
		User user = new User(String.valueOf(iduser), passwordEncoder.encode(piduser), role, 2);
		userRepository.save(user);
		lastRegisterStudentRepository.save(lastRegisterStudent);
		return "redirect:/admin/registered-student";
	}
	
	@PostMapping("/admin/register-teacher")
	public String registerForTeacher(@RequestParam String piduser, @RequestParam String fullname, @RequestParam Date birth, @RequestParam String phone, @RequestParam String mail, @RequestParam String country, @RequestParam String classroom) {
		InformationTeacher informationTeacher = informationTeacherRepository.findInfoTeacherByClassroom(classroom);
		if(informationTeacher != null) {
			ErrorCode errorCode = errorCodeRepository.findErrorById(1);
        	if(errorCode == null) {
        		errorCode = new ErrorCode();
        	}
			errorCode.setErrorcode(1);
			errorCodeRepository.save(errorCode);
			return "redirect:/error";
		}
		informationTeacher = new InformationTeacher(fullname, piduser, birth, phone, mail, country, classroom);
		informationTeacherRepository.save(informationTeacher);
		iduser = informationTeacher.getIduser();
		role = "ROLE_TEACHER";
		User user = new User(String.valueOf(iduser), passwordEncoder.encode(piduser), role, 3);
		userRepository.save(user);
		lastRegisterTeacherRepository.save(lastRegisterTeacher);
		return "redirect:/admin/registered-teacher";
	}
}
