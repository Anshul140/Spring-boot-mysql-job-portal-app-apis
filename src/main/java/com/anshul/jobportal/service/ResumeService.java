package com.anshul.jobportal.service;

import org.springframework.web.multipart.MultipartFile;

import com.anshul.jobportal.entities.Resume;

public interface ResumeService {

	Resume saveResume(MultipartFile file) throws Exception;
	
	Resume getResume(long candidateId) throws Exception;
}
