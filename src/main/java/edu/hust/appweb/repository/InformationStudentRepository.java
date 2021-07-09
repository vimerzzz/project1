package edu.hust.appweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hust.appweb.domain.InformationStudent;

@Repository
public interface InformationStudentRepository extends JpaRepository<InformationStudent, Integer>{
	InformationStudent findInfoStudentByIduser(int iduser);
	InformationStudent findInfoStudentByClassroom(String classroom);
	List<InformationStudent> findInfoStudentByFullname(String fullname);
}
