package com.anshul.jobportal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshul.jobportal.entities.Recruiter;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long>{

	Optional<Recruiter> findByEmail(String email);
}
