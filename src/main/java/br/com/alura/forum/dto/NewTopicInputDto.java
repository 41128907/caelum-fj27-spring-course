package br.com.alura.forum.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class NewTopicInputDto {
	@NotBlank
	@Size(min=10)
	private String shortDescription;
	@NotBlank
	@Size(min=10)
	private String content;
	@NotEmpty
	private String courseName;
	
	public String getShortDescription() {
		return shortDescription;
	}
	public String getContent() {
		return content;
	}
	public String getCourseName() {
		return courseName;
	}
}
