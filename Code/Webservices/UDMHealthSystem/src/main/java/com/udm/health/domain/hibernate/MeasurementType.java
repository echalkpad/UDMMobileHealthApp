package com.udm.health.domain.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="measurement_type")
public class MeasurementType {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idMeasurementType")
	private Long id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="Scale_idScale")
	private Scale scale;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Scale getScale() {
		return scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}
	
}
