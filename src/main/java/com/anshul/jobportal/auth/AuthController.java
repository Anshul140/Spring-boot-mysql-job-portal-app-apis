 package com.anshul.jobportal.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anshul.jobportal.entities.Resume;
import com.anshul.jobportal.service.ResumeService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final AuthenticationService authenticationService;	
	
	@Autowired
	private ResumeService resumeService;
	
	public AuthController(AuthenticationService authenticationService, ResumeService resumeService) {
		this.authenticationService = authenticationService;
		this.resumeService = resumeService;
	}

	@GetMapping("/home")
	public String test() {
		return "Home page unsecured end point";
	}
	
	@PostMapping("/candidate/signUp")
	public ResponseEntity<AuthenticationResponseCandidate> registerCandidate(
		@RequestPart("test_json")RegisterRequestCandidate request,
		@RequestPart("test_file")MultipartFile file
	) throws Exception {
		Resume resume = null;
		String resumeDownloadURL = "";
		resume = resumeService.saveResume(file);
		resumeDownloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/")
				.path(resume.getId()+"")
				.toUriString();
		
		return ResponseEntity.ok(authenticationService.registerCandidate(request, resumeDownloadURL, resume));
	}
	
	@PostMapping("/candidate/signIn")
	public ResponseEntity<AuthenticationResponse> authenticateCandidate(
		@RequestBody AuthenticationRequest request
	) {
		return ResponseEntity.ok(authenticationService.authenticateCandidate(request));
	}
	
	@PostMapping("/recruiter/signUp")
	public ResponseEntity<AuthenticationResponse> registerRecruiter(
		@RequestBody RegisterRequestRecruiter request
	) {
		return ResponseEntity.ok(authenticationService.registerRecruiter(request));
	}
	
	@PostMapping("/recruiter/signIn")
	public ResponseEntity<AuthenticationResponse> authenticateRecruiter(
		@RequestBody AuthenticationRequest request
	) {
		return ResponseEntity.ok(authenticationService.authenticateRecruiter(request));
	}
	
}
