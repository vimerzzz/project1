package edu.hust.appweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hust.appweb.domain.LoggedUser;

@Repository
public interface LoggedUserRepository extends JpaRepository<LoggedUser, Integer> {
	LoggedUser findLoggedUserByIdloggeduser(int idloggeduser);
}