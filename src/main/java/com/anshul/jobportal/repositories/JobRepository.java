package com.anshul.jobportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshul.jobportal.entities.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

}
