package edu.hust.appweb.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hust.appweb.domain.LoggedUser;
import edu.hust.appweb.domain.User;
import edu.hust.appweb.repository.LoggedUserRepository;
import edu.hust.appweb.repository.UserRepository;

@Controller
public class LoginController {
	private String iduser;
	private int rolecode;
	
	@Autowired
	LoggedUserRepository loggedUserRepository;
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/home")
	public String redirect() {
		LoggedUser loggedUser = loggedUserRepository.findLoggedUserByIdloggeduser(1);
		iduser = loggedUser.getLoggeduser();
		User user = userRepository.findUserByUsername(iduser);
		rolecode = user.getRolecode();
		if(rolecode == 1) return "redirect:/admin";
		else if(rolecode == 2) return "redirect:/student";
		else return "redirect:/teacher";
	}

    @RequestMapping("/teacher")
    public String homeForTeacher() {
	return "home-for-teacher";
    }
    
    @RequestMapping("/student")
    public String homeForStudent() {
        return "home-for-student";
    }
    
    @RequestMapping("/admin")
    public String homeForAdmin() {
    	return "home-for-admin";
    }

    @GetMapping({"/", "/login"}) 
    public String getLogin() {
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}