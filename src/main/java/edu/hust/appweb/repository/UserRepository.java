package edu.hust.appweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.hust.appweb.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findUserByUsername(String username);
}
