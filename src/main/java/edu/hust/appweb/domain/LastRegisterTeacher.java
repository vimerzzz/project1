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
@Table(name = "lastregisterteacher")
public class LastRegisterTeacher implements Serializable{
	@Id
	@SequenceGenerator(name = "seqlastregisterteacher", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqlastregisterteacher")
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "iduser", nullable = false)
	private int iduser;
	
	public LastRegisterTeacher(int iduser) {
		this.iduser = iduser;
	}
	
	public LastRegisterTeacher() {
		
	}
	
	public int getIduser() {
		return iduser;
	}
	
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
}
