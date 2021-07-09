package edu.hust.appweb.service;

import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.hust.appweb.domain.User;
import edu.hust.appweb.repository.UserRepository;
import edu.hust.appweb.domain.LoggedUser;
import edu.hust.appweb.repository.LoggedUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 	@Autowired
    private UserRepository userRepository;
 	@Autowired
 	private LoggedUserRepository loggedUserRepository;
 	
 	private String username;
 	
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        this.username = username;
        LoggedUser luser = loggedUserRepository.findLoggedUserByIdloggeduser(1);
        if(luser == null) {
        	luser = new LoggedUser(username);
        }
        else {
        	luser.setLoggeduser(username);
        }
        loggedUserRepository.save(luser);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthorities);
    }
    
    public String getUsername() {
    	return this.username;
    }
    
    public UserDetailsServiceImpl() {
    	
    }
}
