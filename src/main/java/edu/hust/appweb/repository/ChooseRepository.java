package edu.hust.appweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hust.appweb.domain.Choose;

@Repository
public interface ChooseRepository extends JpaRepository<Choose, Integer>{
	Choose findUserById(int id);
}
