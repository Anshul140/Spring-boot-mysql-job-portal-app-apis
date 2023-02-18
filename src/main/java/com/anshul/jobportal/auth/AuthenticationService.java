package com.anshul.jobportal.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anshul.jobportal.config.JwtService;
import com.anshul.jobportal.entities.Candidate;
import com.anshul.jobportal.entities.Recruiter;
import com.anshul.jobportal.entities.Resume;
import com.anshul.jobportal.repositories.CandidateRepository;
import com.anshul.jobportal.repositories.RecruiterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final CandidateRepository candidateRepository;
	private final RecruiterRepository recruiterRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponseCandidate registerCandidate(RegisterRequestCandidate request, String url, Resume resume) throws Exception{
		var candidate = Candidate.builder()
				.name(request.getName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.resume(resume)
				.skills_per_candidate(request.getSkills_per_candidate())
				.build();
		
		candidateRepository.save(candidate);
		var jwtToken = jwtService.generateToken(candidate);
		return AuthenticationResponseCandidate.builder()
				.name(request.getName())
				.email(request.getEmail())
				.downloadResumeLink(url)
				.skills_per_candidate(request.getSkills_per_candidate())
				.token(jwtToken)
				.build();
	}
	
	public AuthenticationResponse authenticateCandidate(AuthenticationRequest request) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getEmail(),
				request.getPassword()
			)	
		);
		
		var candidate = candidateRepository.findByEmail(request.getEmail()).orElseThrow();
		
		var jwtToken = jwtService.generateToken(candidate);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
	
	public AuthenticationResponse registerRecruiter(RegisterRequestRecruiter request) {
		var recruiter = Recruiter.builder()
				.name(request.getName())
				.email(request.getEmail())
				.passWord(passwordEncoder.encode(request.getPassword()))
				.company(request.getCompany())
				.build();
		
		recruiterRepository.save(recruiter);
		var jwtToken = jwtService.generateToken(recruiter);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
	
	public AuthenticationResponse authenticateRecruiter(AuthenticationRequest request) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getEmail(),
				request.getPassword()
			)	
		);
		
		var recruiter = recruiterRepository.findByEmail(request.getEmail())
				.orElseThrow();
		
		var jwtToken = jwtService.generateToken(recruiter);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
}
