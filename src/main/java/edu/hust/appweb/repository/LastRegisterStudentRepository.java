package edu.hust.appweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hust.appweb.domain.LastRegisterStudent;

@Repository
public interface LastRegisterStudentRepository extends JpaRepository<LastRegisterStudent, Integer>{
	LastRegisterStudent findStudentById(int id);
}