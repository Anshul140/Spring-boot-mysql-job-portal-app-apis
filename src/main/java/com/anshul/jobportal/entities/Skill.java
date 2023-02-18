package com.anshul.jobportal.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skill")
@NoArgsConstructor
public class Skill {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	private String name;
	
	@ManyToMany
	@JoinTable(
		name = "skills_per_job",
		joinColumns = @JoinColumn(name = "skill_id"),
		inverseJoinColumns = @JoinColumn(name = "job_id")
	)
	private List<Job> jobs_per_skill = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
		name = "candidates_per_skill", 
	    joinColumns = @JoinColumn(name = "skill_id"),
	    inverseJoinColumns = @JoinColumn(name = "candidate_id")
	)
	private List<Candidate> candidates_per_skill = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Skill(@NotNull String name) {
		super();
		this.name = name;
	}

	
}
