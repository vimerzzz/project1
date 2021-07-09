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
@Table(name = "loggeduser")
public class LoggedUser implements Serializable{
	@Id
	@SequenceGenerator(name = "seqloggeduser", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqloggeduser")
	@Column(name = "idloggeduser", nullable = false)
	private int idloggeduser;
	
	@Column(name = "loggeduser")
	private String loggeduser;
	
	public LoggedUser(String loggeduser) {
		this.loggeduser = loggeduser;
	}
	
	public LoggedUser() {
		
	}
	
	public String getLoggeduser() {
		return loggeduser;
	}
	
	public void setLoggeduser(String loggeduser) {
		this.loggeduser = loggeduser;
	}
}
