package edu.hust.appweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/").anonymous()
            	.antMatchers("/login").anonymous()
                .antMatchers("/student/*").hasRole("STUDENT")
                .antMatchers("/teacher/*").hasRole("TEACHER")
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers("/student").hasRole("STUDENT")
                .antMatchers("/teacher").hasRole("TEACHER")
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/error").hasRole("ADMIN")
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home", false)
                .failureUrl("/login?error")
                .and()
             .logout()
             	.clearAuthentication(true)
             	.logoutUrl("/logout")
             	.logoutSuccessUrl("/")
             	.and()
            .exceptionHandling()	
                .accessDeniedPage("/403");			//không có quyền truy cập
    }
}
