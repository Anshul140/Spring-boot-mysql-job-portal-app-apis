package com.anshul.jobportal.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.anshul.jobportal.entities.Resume;
import com.anshul.jobportal.repositories.ResumeRepository;

@Service
public class ResumeServiceImpl implements ResumeService{

	private ResumeRepository resumeRepository;

	public ResumeServiceImpl(ResumeRepository resumeRepository) {
		this.resumeRepository = resumeRepository;
	}

	@Override
	public Resume saveResume(MultipartFile file) throws Exception {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                + fileName);
            }

            Resume attachment
                    = new Resume(fileName,
                    file.getContentType(),
                    file.getBytes());
            return resumeRepository.save(attachment);

        } catch (Exception e) {
        	System.out.println(e.getMessage());
            throw new Exception("Could not save File: " + fileName);
        }
	}

	@Override
	public Resume getResume(long candidateId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
