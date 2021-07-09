package edu.hust.appweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.hust.appweb.domain.User;
import edu.hust.appweb.repository.UserRepository;

@SpringBootApplication
public class AppDataSeed implements CommandLineRunner{
	public static void main(String[] args) {
        SpringApplication.run(AppDataSeed.class, args);
    }
	@Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {    
    	User u = userRepository.findUserByUsername("20200000");
    	if(u == null) {
    		u = new User();
	        u.setUsername("20200000");
	        u.setPassword(passwordEncoder.encode("admin"));
	        u.setRole("ROLE_ADMIN");
	        u.setRolecode(1);
	        userRepository.save(u);
    	}
    }
}
