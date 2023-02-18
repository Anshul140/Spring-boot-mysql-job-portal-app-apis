package com.anshul.jobportal.entities;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String fileName;
	private String fileType;
	
	@OneToOne(mappedBy = "resume", cascade = CascadeType.ALL)
	private Candidate candidate;
	
	@Lob
	private byte[] data;

	public Resume(String fileName, String fileType, byte[] data) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}
}
