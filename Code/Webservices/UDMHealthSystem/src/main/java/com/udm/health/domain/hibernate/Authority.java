package com.udm.health.domain.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="authority_pk")
	private Long id;
	
	@Column(name="authority")
	private String authorityType;
	
	public Authority() { }
	
	public Authority(String authorityType) {
		this.authorityType = authorityType;
	}

	public String getAuthorityType() {
		return authorityType;
	}
	
	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
