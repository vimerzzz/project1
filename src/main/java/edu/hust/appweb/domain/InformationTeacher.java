package edu.hust.appweb.domain;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "informationteacher")
public class InformationTeacher implements Serializable{
		
	@Id
	@SequenceGenerator(name = "seqteacher", initialValue = 202001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqteacher")
	@Column(name = "iduser", nullable = false)
	private int iduser;

	@Column(name = "fullname", nullable = false)
	private String fullname;
	
	
	@Column(name = "piduser", nullable = false)
	private String piduser;
	
	@Column(name = "birth", nullable = false)
	private Date birth;
	
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name = "mail", nullable = false)
	private String mail;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "classroom", nullable = false)
	private String classroom;
	
	public InformationTeacher(String fullname, String piduser, Date birth, String phone, String mail, String country, String classroom) {
		this.fullname = fullname;
		this.piduser = piduser;
		this.birth = birth;
		this.phone = phone;
		this.mail = mail;
		this.country = country;
		this.classroom = classroom;
	}
	
	public InformationTeacher() {
		
	}
	
	public int getIduser() {
		return iduser;
	}
	
	public String getPiduser() {
		return piduser;
	}
	
	public void setPiduser(String piduser) {
		this.piduser = piduser;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getClassroom() {
		return classroom;
	}
	
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
}
