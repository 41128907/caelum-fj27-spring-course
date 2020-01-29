package br.com.alura.forum.dto;

import java.time.Instant;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.alura.forum.domain.topic.Topic;
import br.com.alura.forum.domain.topic.TopicStatus;

public class TopicOutputDto {
	
	@NotBlank
	@Size(min=2, max=5)
	private Long id;
	@NotBlank
	@Size(min=2, max=15)
	private String shortDescription;
	@NotBlank
	@Size(min=2, max=15)
	private String content;
	private TopicStatus topicStatus;
	private int numberOfResponses;
	private Instant creationInstant;
	private Instant lastUpdate;
	private String ownerName;
	private String courseName;
	private String subcategoryName;
	private String categoryName;
	
	public TopicOutputDto(Topic topic) {
		this.id = topic.getId();
		this.shortDescription = topic.getShortDescription();
		this.content = topic.getContent();
		this.topicStatus = topic.getStatus();
		this.numberOfResponses = topic.getNumberOfAnswers();
		this.creationInstant = topic.getCreationInstant();
		this.lastUpdate = topic.getLastUpdate();
		this.ownerName = topic.getOwnerName();
		this.courseName = topic.getCourse().getName();
		this.subcategoryName = topic.getCourse().getSubcategoryName();
		this.categoryName = topic.getCourse().getCategoryName();
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getShortDescription() {
		return shortDescription;
	}


	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public TopicStatus getTopicStatus() {
		return topicStatus;
	}


	public void setTopicStatus(TopicStatus topicStatus) {
		this.topicStatus = topicStatus;
	}


	public int getNumberOfResponses() {
		return numberOfResponses;
	}


	public void setNumberOfResponses(int numberOfResponses) {
		this.numberOfResponses = numberOfResponses;
	}


	public Instant getCreationInstant() {
		return creationInstant;
	}


	public void setCreationInstant(Instant creationInstant) {
		this.creationInstant = creationInstant;
	}


	public Instant getLastUpdate() {
		return lastUpdate;
	}


	public void setLastUpdate(Instant lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getSubcategoryName() {
		return subcategoryName;
	}


	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
