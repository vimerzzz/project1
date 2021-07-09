package edu.hust.appweb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "lastregisterstudent")
public class LastRegisterStudent implements Serializable{
	@Id
	@SequenceGenerator(name = "seqlastregisterstudent", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqlastregisterstudent")
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "iduser", nullable = false)
	private int iduser;
	
	public LastRegisterStudent(int iduser) {
		this.iduser = iduser;
	}
	
	public LastRegisterStudent() {
		
	}
	
	public int getIduser() {
		return iduser;
	}
	
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
}
