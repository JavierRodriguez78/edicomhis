package com.edicom.hisedicom.domain.entities;

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
@Table(name="doctors")
public class Doctor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String lastname;
	
	@NotEmpty
	private String specialty;
	
	@NotEmpty
	private String collegiatenumber;
	

	@NotNull
	@Column (name="created_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	

	@Column (name="updated_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	public Doctor() {
		super();
	}

	
	
	public Doctor(Long id, @NotEmpty String name, @NotEmpty String lastname, @NotEmpty String specialty,
			@NotEmpty String collegiatenumber, @NotNull Date createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.specialty = specialty;
		this.collegiatenumber = collegiatenumber;
		this.createdAt = createdAt;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	
	

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getCollegiatenumber() {
		return collegiatenumber;
	}

	public void setCollegiatenumber(String collegiatenumber) {
		this.collegiatenumber = collegiatenumber;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", lastname=" + lastname + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	
	
}
