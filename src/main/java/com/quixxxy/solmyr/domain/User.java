package com.quixxxy.solmyr.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "USERS")
public class User implements Serializable, Cloneable{

	private static final long serialVersionUID = -1031086869916246756L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(name = "USERNAME")
	private String username;
	
	@NotBlank
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "EMAIL")
	@Email
	@NotBlank
	private String email;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private Collection<SecurityRole> securityRoles = new LinkedHashSet<SecurityRole>();
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Collection<SecurityRole> getSecurityRoles() {
		return securityRoles;
	}

	public void setSecurityRoles(Collection<SecurityRole> securityRoles) {
		this.securityRoles = securityRoles;
	}
	
	public String toString() {
		return new ToStringBuilder(this).
				append("id").append(id).
				append("username").append(username).
				append("password").append(password).
				append("email").append(email).
				append("isActive").append(isActive).
				toString();
	}
}
