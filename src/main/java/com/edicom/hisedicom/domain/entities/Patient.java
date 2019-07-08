package com.edicom.hisedicom.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="patients")
public class Patient implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(unique=true)
	private String medicalRecord;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String lastname;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="doctor_id", nullable=false)
	private Doctor doctor;
	
	

	@NotNull
	@Column (name="created_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	

	@Column (name="updated_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;


	public Patient() {
		
	}



	public Patient(@NotEmpty String medicalRecord, @NotEmpty String name, @NotEmpty String lastname, Doctor doctor,
			@NotNull Date createdAt) {
		super();
		this.medicalRecord = medicalRecord;
		this.name = name;
		this.lastname = lastname;
		this.doctor = doctor;
		this.createdAt = createdAt;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMedicalRecord() {
		return medicalRecord;
	}


	public void setMedicalRecord(String medicalRecord) {
		this.medicalRecord = medicalRecord;
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
	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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
		return "Patient [id=" + id + ", medicalRecord=" + medicalRecord + ", name=" + name + ", lastname=" + lastname
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
	
	
}
