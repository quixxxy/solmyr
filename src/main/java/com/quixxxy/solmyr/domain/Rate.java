package com.quixxxy.solmyr.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "RATES")
public class Rate implements Serializable, Cloneable {
	
	private static final long serialVersionUID = -9112094630969045112L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "RATE")
	@Min(value = 0, message = "{validation.rate.min}")
	@Max(value = 100, message = "{validation.rate.max}")
	private int rate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public String toString() {
		return "Id: " + id + " Rate: " + rate;
	}
}
