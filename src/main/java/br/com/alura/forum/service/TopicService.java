package br.com.alura.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.forum.controller.dto.input.NewTopicInputDto;
import br.com.alura.forum.domain.Course;
import br.com.alura.forum.domain.User;
import br.com.alura.forum.domain.topic.Topic;
import br.com.alura.forum.dto.TopicOutputDto;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private CourseRepository courseRepository;

	public Page<Topic> findAll(Specification<Topic> topicSearchSpecification, Pageable pageable) {
		return topicRepository.findAll(topicSearchSpecification, pageable);
	}

	public TopicOutputDto createTopic(NewTopicInputDto newTopicDto, User user) {
		Course course = courseRepository.findByName(newTopicDto.getCourseName());
		Topic topic = new Topic(newTopicDto.getShortDescription(), newTopicDto.getContent(), user, course);
		
		return new TopicOutputDto(topicRepository.save(topic));
	}

}
