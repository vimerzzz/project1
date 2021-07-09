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
@Table(name = "choose")
public class Choose implements Serializable{
	@Id
	@SequenceGenerator(name = "seqchoose", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqchoose")
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "iduser", nullable = false)
	private int iduser;
	
	@Column(name = "fullname", nullable = false)
	private String fullname;
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public int getIduser() {
		return iduser;
	}
	
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
}
