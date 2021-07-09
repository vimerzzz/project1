package edu.hust.appweb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable{
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	@Column(name = "rolecode")
	private int rolecode;
	
	public User(String username, String password, String role, int rolecode) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.rolecode = rolecode;
	}
	
	public User() {
		
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public int getRolecode() {
		return rolecode;
	}
	
	public void setRolecode(int rolecode) {
		this.rolecode = rolecode;
	}
}
