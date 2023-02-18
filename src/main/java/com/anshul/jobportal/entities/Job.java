package com.anshul.jobportal.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "job")
public class Job {

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	private String company;
	
	@NotNull
	private String salary;
	
	@NotNull
	private String title;
	
	@ManyToMany
	@JoinTable(
		name = "candidates_per_job",
		joinColumns = @JoinColumn(name = "job_id"),
		inverseJoinColumns = @JoinColumn(name = "candidate_id")
	)
	private List<Candidate> candidates_per_job = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiter;
	
	@ManyToMany
	@JoinTable(
		name = "skills_per_job",
		joinColumns = @JoinColumn(name = "job_id"),
		inverseJoinColumns = @JoinColumn(name = "skill_id")
	)
	private List<Skill> skills_per_job = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

	public List<Candidate> getCandidates_per_job() {
		return candidates_per_job;
	}

	public void setCandidates_per_job(List<Candidate> candidates_per_job) {
		this.candidates_per_job = candidates_per_job;
	}

	public List<Skill> getSkills_per_job() {
		return skills_per_job;
	}

	public void setSkills_per_job(List<Skill> skills_per_job) {
		this.skills_per_job = skills_per_job;
	}

	
}
