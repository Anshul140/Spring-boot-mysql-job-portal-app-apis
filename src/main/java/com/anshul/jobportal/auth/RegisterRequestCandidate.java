package com.anshul.jobportal.auth;

import java.util.List;

import com.anshul.jobportal.entities.Resume;
import com.anshul.jobportal.entities.Skill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestCandidate {

	private String name;
	private String email;
	private String password;
	private List<Skill> skills_per_candidate;
}
