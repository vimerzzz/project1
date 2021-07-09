package edu.hust.appweb.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hust.appweb.domain.Choose;
import edu.hust.appweb.domain.InformationStudent;
import edu.hust.appweb.domain.InformationTeacher;
import edu.hust.appweb.domain.LastRegisterStudent;
import edu.hust.appweb.domain.LastRegisterTeacher;
import edu.hust.appweb.domain.LoggedUser;
import edu.hust.appweb.repository.ChooseRepository;
import edu.hust.appweb.repository.InformationStudentRepository;
import edu.hust.appweb.repository.InformationTeacherRepository;
import edu.hust.appweb.repository.LastRegisterStudentRepository;
import edu.hust.appweb.repository.LastRegisterTeacherRepository;
import edu.hust.appweb.repository.LoggedUserRepository;

@Controller
public class InformationController {
	private int iduser;
	private String piduser;
	private String fullname;
	private String phone;
	private Date birth;
	private String mail;
	private String country;
    private String classroom;
    private String teacher;
	
    @Autowired
    ChooseRepository chooseRepository;
	@Autowired
	InformationStudentRepository informationStudentRepository;
	@Autowired
	InformationTeacherRepository informationTeacherRepository;
	@Autowired
	LastRegisterStudentRepository lastRegisterStudentRepository;
	@Autowired
	LastRegisterTeacherRepository lastRegisterTeacherRepository;
	@Autowired
	LoggedUserRepository loggedUserRepository;
	
	@RequestMapping("/student/information")
	public String showInformationStudent(Model model) {
		LoggedUser loggedUser = loggedUserRepository.findLoggedUserByIdloggeduser(1);
		iduser = Integer.parseInt(loggedUser.getLoggeduser());
		InformationStudent informationStudent = informationStudentRepository.findInfoStudentByIduser(iduser);
		piduser = informationStudent.getPiduser();
		fullname = informationStudent.getFullname();
		phone = informationStudent.getPhone();
		country = informationStudent.getCountry();
		birth = informationStudent.getBirth();
		mail = informationStudent.getMail();
		classroom = informationStudent.getClassroom();
		InformationTeacher informationTeacher = informationTeacherRepository.findInfoTeacherByClassroom(classroom);
		if(informationTeacher == null) {
			teacher = "Chưa có";
		}
		else {
			teacher = informationTeacher.getFullname();
		}
		model.addAttribute("iduser", iduser);
		model.addAttribute("piduser", piduser);
		model.addAttribute("fullname", fullname);
		model.addAttribute("birth", birth);
		model.addAttribute("phone", phone);
		model.addAttribute("mail", mail);
		model.addAttribute("country", country);
        model.addAttribute("classroom", classroom);
        model.addAttribute("teacher", teacher);
		return "information-student";
	}

	@RequestMapping("/teacher/information")
	public String showInformationTeacher(Model model) {
		LoggedUser loggedUser = loggedUserRepository.findLoggedUserByIdloggeduser(1);
		iduser = Integer.parseInt(loggedUser.getLoggeduser());
		InformationTeacher informationTeacher = informationTeacherRepository.findInfoTeacherByIduser(iduser);
		piduser = informationTeacher.getPiduser();
		fullname = informationTeacher.getFullname();
		phone = informationTeacher.getPhone();
		country = informationTeacher.getCountry();
		birth = informationTeacher.getBirth();
		mail = informationTeacher.getMail();
		classroom = informationTeacher.getClassroom();
		model.addAttribute("iduser", iduser);
		model.addAttribute("piduser", piduser);
		model.addAttribute("fullname", fullname);
		model.addAttribute("birth", birth);
		model.addAttribute("phone", phone);
		model.addAttribute("mail", mail);
		model.addAttribute("country", country);
        model.addAttribute("classroom", classroom);
		return "information-teacher";
	}
	
	@RequestMapping("/admin/registered-student")
	public String registeredStudent(Model model) {
		LastRegisterStudent lastRegisterStudent = lastRegisterStudentRepository.findStudentById(1);
		iduser = lastRegisterStudent.getIduser();
		InformationStudent informationStudent = informationStudentRepository.findInfoStudentByIduser(iduser);
		piduser = informationStudent.getPiduser();
		fullname = informationStudent.getFullname();
		phone = informationStudent.getPhone();
		country = informationStudent.getCountry();
		birth = informationStudent.getBirth();
		mail = informationStudent.getMail();
		classroom = informationStudent.getClassroom();
		InformationTeacher informationTeacher = informationTeacherRepository.findInfoTeacherByClassroom(classroom);
		if(informationTeacher == null) {
			teacher = "Chưa có";
		}
		else {
			teacher = informationTeacher.getFullname();
		}
		model.addAttribute("iduser", iduser);
		model.addAttribute("piduser", piduser);
		model.addAttribute("fullname", fullname);
		model.addAttribute("birth", birth);
		model.addAttribute("phone", phone);
		model.addAttribute("mail", mail);
		model.addAttribute("country", country);
		model.addAttribute("classroom", classroom);
		model.addAttribute("teacher", teacher);
		Choose choose = chooseRepository.findUserById(1);
        if(choose == null) {
        	choose = new Choose();
        }
        choose.setIduser(iduser);
        chooseRepository.save(choose);
		return "registered-student";
	}
	
	@RequestMapping("/admin/registered-teacher")
	public String registeredTeacher(Model model) {
		LastRegisterTeacher lastRegisterTeacher = lastRegisterTeacherRepository.findTeacherById(1);
		iduser = lastRegisterTeacher.getIduser();
		InformationTeacher informationTeacher = informationTeacherRepository.findInfoTeacherByIduser(iduser);
		piduser = informationTeacher.getPiduser();
		fullname = informationTeacher.getFullname();
		phone = informationTeacher.getPhone();
		country = informationTeacher.getCountry();
		birth = informationTeacher.getBirth();
		mail = informationTeacher.getMail();
		classroom = informationTeacher.getClassroom();
		model.addAttribute("iduser", iduser);
		model.addAttribute("piduser", piduser);
		model.addAttribute("fullname", fullname);
		model.addAttribute("birth", birth);
		model.addAttribute("phone", phone);
		model.addAttribute("mail", mail);
		model.addAttribute("country", country);
		model.addAttribute("classroom", classroom);
		Choose choose = chooseRepository.findUserById(1);
        if(choose == null) {
        	choose = new Choose();
        }
        choose.setIduser(iduser);
        chooseRepository.save(choose);
		return "registered-teacher";
	}
}
