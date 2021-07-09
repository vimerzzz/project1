package edu.hust.appweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hust.appweb.domain.LastRegisterTeacher;

@Repository
public interface LastRegisterTeacherRepository extends JpaRepository<LastRegisterTeacher, Integer>{
	LastRegisterTeacher findTeacherById(int id);
}
