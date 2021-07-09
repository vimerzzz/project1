package edu.hust.appweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.hust.appweb.domain.Choose;
import edu.hust.appweb.domain.ErrorCode;
import edu.hust.appweb.domain.InformationStudent;
import edu.hust.appweb.domain.InformationTeacher;
import edu.hust.appweb.repository.ChooseRepository;
import edu.hust.appweb.repository.ErrorCodeRepository;
import edu.hust.appweb.repository.InformationStudentRepository;
import edu.hust.appweb.repository.InformationTeacherRepository;

@Controller
public class ChooseController {
	@Autowired
	ChooseRepository chooseRepository;
	@Autowired
	ErrorCodeRepository errorCodeRepository;
    @Autowired
    InformationStudentRepository informationStudentRepository;
    @Autowired
    InformationTeacherRepository informationTeacherRepository;
    
    @RequestMapping("/admin/choose-student")
    public String chooseStudent(){
        return "choose-student";
    }
    
    @RequestMapping("/admin/choose-teacher")
    public String chooseTeacher(){
        return "choose-teacher";
    }
    
    @PostMapping("/admin/choose-student")
    public String chosenStudent(@RequestParam String fullname){
    	Choose choose = chooseRepository.findUserById(1);
        if(choose == null) {
        	choose = new Choose();
        }
        choose.setFullname(fullname);
        chooseRepository.save(choose);
        List<InformationStudent> informationStudent = informationStudentRepository.findInfoStudentByFullname(fullname);
        if(informationStudent == null) {
        	ErrorCode errorCode = errorCodeRepository.findErrorById(1);
        	if(errorCode == null) {
        		errorCode = new ErrorCode();
        	}
			errorCode.setErrorcode(2);
			errorCodeRepository.save(errorCode);
        	return "redirect:/error";
        }
        return "redirect:/admin/change-information-student";
    }
    
    @PostMapping("/admin/choose-teacher")
    public String chosenTeacher(@RequestParam int iduser){
    	Choose choose = chooseRepository.findUserById(1);
        if(choose == null) {
        	choose = new Choose();
        }
        choose.setIduser(iduser);
        chooseRepository.save(choose);
        InformationTeacher informationTeacher = informationTeacherRepository.findInfoTeacherByIduser(iduser);
        if(informationTeacher == null) {
        	ErrorCode errorCode = errorCodeRepository.findErrorById(1);
        	if(errorCode == null) {
        		errorCode = new ErrorCode();
        	}
			errorCode.setErrorcode(3);
			errorCodeRepository.save(errorCode);
        	return "redirect:/error";
        }
        return "redirect:/admin/change-information-teacher";
    }
}
