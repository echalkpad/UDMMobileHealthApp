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
@Table(name="user_authorities")
public class UserAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_authority_pk")
	private Long id;

	@ManyToOne
	@JoinColumn(name="authority_pk")
	private Authority authority;
	
	@Column(name="username")
	private String username;
	
	public UserAuthority() { }
	
	public UserAuthority(String username, Authority authority) {
		this.username = username;
		this.authority = authority;
	}

	public Authority getAuthority() {
		return authority;
	}
	
	public void setAuthority(Authority authority) {
		this.authority = authority;
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
}
