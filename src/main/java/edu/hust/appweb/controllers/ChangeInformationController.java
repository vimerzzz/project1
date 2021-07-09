package edu.hust.appweb.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.hust.appweb.domain.Choose;
import edu.hust.appweb.domain.ErrorCode;
import edu.hust.appweb.domain.InformationStudent;
import edu.hust.appweb.domain.InformationTeacher;
import edu.hust.appweb.domain.User;
import edu.hust.appweb.repository.ChooseRepository;
import edu.hust.appweb.repository.ErrorCodeRepository;
import edu.hust.appweb.repository.InformationStudentRepository;
import edu.hust.appweb.repository.InformationTeacherRepository;
import edu.hust.appweb.repository.UserRepository;

@Controller
public class ChangeInformationController {
    private int iduser;
    private String piduser;
    private String fullname;
    private String phone;
    private Date birth;
    private String mail;
    private String country;
    private String classroom;
    private String teacher;
    private List<InformationStudent> informationStudent;
    private InformationTeacher informationTeacher;
    private User user;

    @Autowired
    ChooseRepository chooseRepository;
    @Autowired
    ErrorCodeRepository errorCodeRepository;
    @Autowired
    InformationStudentRepository informationStudentRepository;
    @Autowired
    InformationTeacherRepository informationTeacherRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/admin/change-information-student")
    public String changeInformationForStudent(Model model){
    	Choose choose = chooseRepository.findUserById(1);
        fullname = choose.getFullname();
        informationStudent = informationStudentRepository.findInfoStudentByFullname(fullname);
        piduser = informationStudent.get(0).getPiduser();
        iduser = informationStudent.get(0).getIduser();
        phone = informationStudent.get(0).getPhone();
        country = informationStudent.get(0).getCountry();
        birth = informationStudent.get(0).getBirth();
        mail = informationStudent.get(0).getMail();
        classroom = informationStudent.get(0).getClassroom();
        model.addAttribute("iduser", iduser);
        model.addAttribute("piduser", piduser);
        model.addAttribute("fullname", fullname);
        model.addAttribute("birth", birth);
        model.addAttribute("phone", phone);
        model.addAttribute("mail", mail);
        model.addAttribute("country", country);
        model.addAttribute("classroom", classroom);
        return "change-information-student";
    }

    @RequestMapping("/admin/change-information-teacher")
    public String changeInformationForTeacher(Model model){
    	Choose choose = chooseRepository.findUserById(1);
        iduser = choose.getIduser();
        informationTeacher = informationTeacherRepository.findInfoTeacherByIduser(iduser);
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
        return "change-information-teacher";
    }

    @PostMapping("/admin/change-information-student")
    public String getChangeInformationForStudent(@RequestParam String piduserafter, @RequestParam String classroomafter, @RequestParam String fullnameafter, @RequestParam Date birthafter, @RequestParam String phoneafter, @RequestParam String mailafter, @RequestParam String countryafter){
        if(piduser != piduserafter){
            user = userRepository.findUserByUsername(String.valueOf(iduser));
            user.setPassword(passwordEncoder.encode(piduserafter));
        }
        informationStudent.get(0).setPiduser(piduserafter);
        informationStudent.get(0).setBirth(birthafter);
        informationStudent.get(0).setCountry(countryafter);
        informationStudent.get(0).setFullname(fullnameafter);
        informationStudent.get(0).setMail(mailafter);
        informationStudent.get(0).setPhone(phoneafter);
        informationStudent.get(0).setClassroom(classroomafter);
        informationStudentRepository.save(informationStudent.get(0));
        userRepository.save(user);
        piduser = piduserafter;
        birth = birthafter;
        classroom = classroomafter;
        country = countryafter;
        phone = phoneafter;
        mail = mailafter;
        fullname = fullnameafter;
        return "redirect:/admin/changed-information-student";
    }

    @PostMapping("/admin/change-information-teacher")
    public String getChangeInformationForTeacher(@RequestParam String piduserafter, @RequestParam String classroomafter, @RequestParam String fullnameafter, @RequestParam Date birthafter, @RequestParam String phoneafter, @RequestParam String mailafter, @RequestParam String countryafter){
    	informationTeacher = informationTeacherRepository.findInfoTeacherByClassroom(classroom);
		if(!classroom.equals(classroomafter)) {
			InformationTeacher informationTeacher2 = informationTeacherRepository.findInfoTeacherByClassroom(classroomafter);
	    	if(informationTeacher2 != null) {
				ErrorCode errorCode = errorCodeRepository.findErrorById(1);
	        	if(errorCode == null) {
	        		errorCode = new ErrorCode();
	        	}
				errorCode.setErrorcode(1);
				errorCodeRepository.save(errorCode);
				return "redirect:/error";
			}
		}
    	if(!piduser.equals(piduserafter)){
            user = userRepository.findUserByUsername(String.valueOf(iduser));
            user.setPassword(passwordEncoder.encode(piduserafter));
        }
        informationTeacher.setPiduser(piduserafter);
        informationTeacher.setBirth(birthafter);
        informationTeacher.setCountry(countryafter);
        informationTeacher.setFullname(fullnameafter);
        informationTeacher.setMail(mailafter);
        informationTeacher.setPhone(phoneafter);
        informationTeacher.setClassroom(classroomafter);
        informationTeacherRepository.save(informationTeacher);
        userRepository.save(user);
        piduser = piduserafter;
        birth = birthafter;
        classroom = classroomafter;
        country = countryafter;
        phone = phoneafter;
        mail = mailafter;
        fullname = fullnameafter;
        return "redirect:/admin/changed-information-teacher";
    }

    @RequestMapping("/admin/changed-information-student")
    public String changedInformationForStudent(Model model){
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
        return "changed-information-student";
    }

    @RequestMapping("/admin/changed-information-teacher")
    public String changedInformationForTeacher(Model model){
        model.addAttribute("iduser", iduser);
        model.addAttribute("piduser", piduser);
        model.addAttribute("fullname", fullname);
        model.addAttribute("birth", birth);
        model.addAttribute("phone", phone);
        model.addAttribute("mail", mail);
        model.addAttribute("country", country);
        model.addAttribute("classroom", classroom);
        return "changed-information-teacher";
    }
}
