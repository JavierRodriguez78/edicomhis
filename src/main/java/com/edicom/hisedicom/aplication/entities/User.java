package com.edicom.hisedicom.aplication.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Column(unique=true)
	private String username;
	@NotEmpty
	private String password;
	
	private String token;


	@NotNull
	@Column (name="created_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	

	@Column (name="updated_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	
	public User() {
		super();
	}

	


	











	public User(@NotEmpty String username, @NotEmpty String password, String token, @NotNull Date createdAt) {
		super();
		this.username = username;
		this.password = password;
		this.token = token;
		this.createdAt = createdAt;
	}
















	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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




	public String getToken() {
		return token;
	}




	public void setToken(String token) {
		this.token = token;
	}




	public Date getCreatedAt() {
		return createdAt;
	}
















	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
















	public Date getUpdatedAt() {
		return updatedAt;
	}
















	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}






	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

	
	
	
	
	
}
