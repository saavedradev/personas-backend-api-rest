package com.saavedradev.personas.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "people")
public class Person implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="no puede estar vacio")
	@Column(nullable = false, unique = true)
	private String identification;
	
	@NotEmpty(message="no puede estar vacio")
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = false)
	private Date birth;
	
	@NotEmpty(message="no puede estar vacio")
	@Column(nullable = false)
	private String gender; // M for male, F for female
	
	@Column(nullable = false)
	private String fatherId;
	@Column(nullable = false)
	private String motherId;
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFatherId() {
		return fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public String getMotherId() {
		return motherId;
	}

	public void setMotherId(String motherId) {
		this.motherId = motherId;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	
	public void adopt(Person child) {
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
