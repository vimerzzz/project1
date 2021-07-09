package edu.hust.appweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.hust.appweb.domain.ErrorCode;

@Repository
public interface ErrorCodeRepository extends JpaRepository<ErrorCode, Integer>{
	ErrorCode findErrorById(int id);
}
