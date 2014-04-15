package com.udm.health.domain.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="services")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_service")
	private Long id;
	
	@Column(name="service_name")
	private String serviceName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	
	@Column(name="logging")
	private Boolean logging;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
	public Boolean getLogging() {
		return logging;
	}
	
	public void setLogging(Boolean logging) {
		this.logging = logging;
	}
	
}
