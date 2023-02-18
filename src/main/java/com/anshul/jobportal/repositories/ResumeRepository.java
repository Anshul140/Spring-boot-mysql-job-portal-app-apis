package com.anshul.jobportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshul.jobportal.entities.Resume;

public interface ResumeRepository extends JpaRepository<Resume, String>{

}
