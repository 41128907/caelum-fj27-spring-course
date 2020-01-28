package br.com.alura.forum.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role  implements GrantedAuthority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final Role ROLE_ADMIN = new Role("ROLE_ADMIN");
	public static final Role ROLE_ALUNO = new Role("ROLE_ALUNO");
	
	@Id
	private String authorithy;
	
	/*
	 * @deprecated
	 */
	public Role() {
	}
	
	public Role(String authorithy) {
		this.authorithy = authorithy;
	}

	@Override
	public String getAuthority() {
		return this.authorithy;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		
		return Objects.equals(authorithy, other);
	}
	

}
