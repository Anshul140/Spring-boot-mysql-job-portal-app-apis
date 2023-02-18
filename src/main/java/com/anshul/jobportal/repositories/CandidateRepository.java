package com.anshul.jobportal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshul.jobportal.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, String>{

	Optional<Candidate> findByEmail(String email);
}
