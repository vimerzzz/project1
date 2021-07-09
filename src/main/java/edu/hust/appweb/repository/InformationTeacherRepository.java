package edu.hust.appweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hust.appweb.domain.InformationTeacher;

@Repository
public interface InformationTeacherRepository extends JpaRepository<InformationTeacher, Integer> {
	InformationTeacher findInfoTeacherByIduser(int iduser);
	InformationTeacher findInfoTeacherByClassroom(String classroom);
}
