package com.learn.SpareZone.Entities;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(nullable = false)
	@NotBlank(message = "Name must not be empty")
    @Pattern(
        regexp = "^[A-Za-z ]+$",
        message = "Name must contain only alphabets and spaces"
    )
	private String name;
	@Column(nullable = false, unique = true)
	
	private String email;
	@Column(nullable = false)
	private String password;
	private LocalDateTime createAt=LocalDateTime.now();
	@ManyToOne
	private Role role;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonBackReference
	private Cart cart;

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
	return Arrays.asList(new SimpleGrantedAuthority(role.getRoleName().toString()));
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	@Override
	public String getPassword() {
		return password;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}

}
