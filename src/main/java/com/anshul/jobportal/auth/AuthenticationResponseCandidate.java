package com.anshul.jobportal.auth;

import java.util.List;

import com.anshul.jobportal.entities.Skill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseCandidate{

	private String name;
	private String email;
	private String downloadResumeLink;
	private List<Skill> skills_per_candidate;
	private String token;
}
