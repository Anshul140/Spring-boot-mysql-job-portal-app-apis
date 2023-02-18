package com.anshul.jobportal.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "candidate")
@NoArgsConstructor
@AllArgsConstructor
public class Candidate implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	// object of resume class
	@OneToOne
	@JoinColumn(name = "resume_id")
	private Resume resume;
	
	
	// list of jobId(s) which candidate has applied
	@ManyToMany
	@JoinTable(
		name = "jobs_per_candidate",
		joinColumns = @JoinColumn(name = "candidate_id"),
		inverseJoinColumns = @JoinColumn(name = "job_id")
	)
	private List<Job> jobs_per_candidate = new ArrayList<>();
	
	
	// list of skills id which candidate posses
	@ManyToMany
	@JoinTable(
		name = "skills_per_candidate",
		joinColumns = @JoinColumn(name = "candidate_id"),
		inverseJoinColumns = @JoinColumn(name = "skill_id")
	)
	private List<Skill> skills_per_candidate = new ArrayList<>();


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@Override
	public String getPassword() {
		return password;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
