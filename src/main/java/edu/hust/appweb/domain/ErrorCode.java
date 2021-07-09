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
@Table(name = "errorcode")
public class ErrorCode implements Serializable{
	@Id
	@SequenceGenerator(name = "seqerror", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqerror")
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "errorcode", nullable = false)
	private int errorcode;
	
	public int getErrorcode() {
		return errorcode;
	}
	
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
}
