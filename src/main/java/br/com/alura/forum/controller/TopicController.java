package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.input.TopicSearchInputDto;
import br.com.alura.forum.domain.topic.Topic;
import br.com.alura.forum.dto.DashboardDto;
import br.com.alura.forum.dto.TopicBriefOutputDto;
import br.com.alura.forum.exception.ResourceNotFoundException;
import br.com.alura.forum.repository.CategoryRepository;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.service.DashboardService;

@RestController
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private DashboardService dasboardService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<TopicBriefOutputDto> listTopics(TopicSearchInputDto topicSearch, 
			@PageableDefault(sort="creationInstant", direction = Sort.Direction.DESC) Pageable pageable) {
		Specification<Topic> topicSearchSpecification = topicSearch.build();
		Page<Topic> topics = topicRepository.findAll(topicSearchSpecification, pageable);
		
		if (topics == null || topics.getTotalElements() == 0) {
			throw new ResourceNotFoundException();
		}
		
		return TopicBriefOutputDto.listFromTopics(topics);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/api/topics/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DashboardDto> listTopicsDashboard(TopicSearchInputDto topicSearch, 
			@PageableDefault(sort="creationInstant", direction = Sort.Direction.DESC) Pageable pageable) {
		
		return dasboardService.findAllDashboard();
	}
}